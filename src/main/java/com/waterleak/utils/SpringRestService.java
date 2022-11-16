package com.waterleak.utils;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SpringRestService {
    static String lastUrl = "Initial";
    static long lastCallTime = System.currentTimeMillis();
    private static String serverUrl;

    @Value("#{'${gateway.protocol}://${gateway.hostname}:${gateway.port}${gateway.contextPath}'}")
    public void setSeverUrl(String url) {
        serverUrl = url;
    }

    private static ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                HttpClients.createDefault());
        factory.setReadTimeout(1000 * 60 * 10);
        factory.setConnectTimeout(1000 * 20);
        return factory;
    }

    public static String doRest(String serviceName, String uri, Object parms, String httpMethod) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        if (parms != null) {
            entity = new HttpEntity<String>(parms.toString(), headers);
        }

        ResponseEntity<String> responseEntity = null;
        try {
            final String url = String.format("%s%s%s", serverUrl, serviceName, uri);
            if (System.currentTimeMillis() - lastCallTime < 200 && lastUrl.equals(url)) {
                return "";
            } else {
                lastCallTime = System.currentTimeMillis();
                lastUrl = url;
            }

            RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
            restTemplate.setMessageConverters(messageConverters);

            switch (httpMethod) {
                case "GET": responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class); break;
                case "POST": responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class); break;
                case "PUT": responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class); break;
                case "DELETE": responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class); break;
            }

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if (responseEntity.getBody() != null) {
                    return responseEntity.getBody();
                } else if (responseEntity.getStatusCode().value() == HttpStatus.NO_CONTENT.value()) {
                    return Integer.toString(HttpStatus.NO_CONTENT.value());
                } else {
                    return "Empty";
                }
            } else {
                return "Error " + responseEntity.getBody();
            }
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            return e.getResponseBodyAsString();
        }
    }

    public static String doAsyncRest(String serviceName, String uri, Object parms, String httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        if (parms != null) {
            entity = new HttpEntity<String>(parms.toString(), headers);
        }
        ListenableFuture<ResponseEntity<String>> futureEntity = null;
        ResponseEntity<String> responseEntity = null;

        try {
            final String url = String.format("%s%s%s", serverUrl, serviceName, uri);
            AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
            asyncRestTemplate.setMessageConverters(messageConverters);

            switch (httpMethod) {
                case "GET": futureEntity = asyncRestTemplate.exchange(url, HttpMethod.GET, entity, String.class); break;
                case "POST": futureEntity = asyncRestTemplate.exchange(url, HttpMethod.POST, entity, String.class); break;
                case "PUT": futureEntity = asyncRestTemplate.exchange(url, HttpMethod.PUT, entity, String.class); break;
                case "DELETE": futureEntity = asyncRestTemplate.exchange(url, HttpMethod.DELETE, entity, String.class); break;
            }

            try {
                responseEntity = futureEntity.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if (responseEntity.getBody() != null) {
                    return responseEntity.getBody();
                } else {
                    return "Empty";
                }
            } else {
                return "Error" + responseEntity.getBody();
            }

        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            return "HttpServerErrorException" + e.getResponseBodyAsString();
        }
    }
}