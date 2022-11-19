package com.waterleak.model.reporting;

import com.waterleak.dto.AckNbiotDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "ack_nbiot")
@Entity
public class AckNbiot {
    @Id
    @Column(name = "IMEI", unique=true, length = 50)
    private String imei;

    @Column(name = "NB_INSTRUCTION", length = 50)
    private String nbInstruction;

    @CreatedDate
    @Column(name = "INSERT_DATE", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime insertDate;

    @Column(name = "UPDATE_DATE", nullable = true, updatable = true)
    private LocalDateTime updateDate;

    @Builder
    public AckNbiot(String imei, String nbInstruction, LocalDateTime insertDate, LocalDateTime updateDate) {
        this.imei = imei;
        this.nbInstruction = nbInstruction;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }

    public AckNbiotDto convertToDto() {
        AckNbiotDto.AckNbiotDtoBuilder builder = AckNbiotDto.builder();
        builder.imei(this.imei);
        builder.nbInstruction(this.nbInstruction);
        return builder.build();
    }
}
