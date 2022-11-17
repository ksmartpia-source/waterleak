package com.waterleak.dto;

import com.waterleak.model.reporting.AckNbiot;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AckNbiotDto {
    private String imei;
    private String nbInstruction;
    private String insertDateStr;
    private String updateDateStr;

    @Builder
    public AckNbiotDto(String imei, String nbInstruction,
                       LocalDateTime insertDateSt, LocalDateTime updateDate) {
        this.imei = imei;
        this.nbInstruction = nbInstruction;
    }

    public AckNbiot convertToEntity() {
        AckNbiot.AckNbiotBuilder builder = AckNbiot.builder();
        if (!StringUtils.hasText(this.imei))
            throw new RuntimeException("IMEM를 확인해 주세요");
        if (!StringUtils.hasText(this.nbInstruction))
            throw new RuntimeException("주기변경 명령어를 확인해 주세요");

        builder.nbInstruction(this.nbInstruction);
        builder.imei(this.imei);
        builder.insertDate(LocalDateTime.now());
        return builder.build();
    }
}
