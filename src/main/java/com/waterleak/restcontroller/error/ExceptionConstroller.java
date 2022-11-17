package com.waterleak.restcontroller.error;

import com.waterleak.config.Globals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionConstroller {
   @ExceptionHandler(RuntimeException.class)
   protected ResponseEntity handleException(RuntimeException e) {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put(Globals.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseMap.put(Globals.MESSAGE, e.getMessage());
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseMap);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e) {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put(Globals.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseMap.put(Globals.MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseMap);
    }
}
