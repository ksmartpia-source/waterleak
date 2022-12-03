package com.waterleak.model.reporting;

import com.waterleak.model.reporting.id.MeterDataSeoulNbiotPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@IdClass(MeterDataSeoulNbiotPK.class)
@Table(name = "meterdataseoulnbiot")
@Entity
public class MeterDataSeoulNbiot implements Serializable {
    @Id
    @Column(name = "imei", length = 50)
    private String imei;

    @Id
    @Column(name = "metering_date")
    private Timestamp meteringDate;

    @Column(name = "receiving_date")
    private Timestamp receivingDate;

    @Column(name = "imsi", length = 50)
    private String imsi;

    @Column(name = "metering_value")
    private BigDecimal meteringValue;

    @Builder
    public MeterDataSeoulNbiot(String imei, Timestamp meteringDate, String imsi,
        BigDecimal meteringValue, Timestamp receivingDate) {
        this.imei = imei;
        this.meteringDate = meteringDate;
        this.imsi = imsi;
        this.meteringValue = meteringValue;
        this.receivingDate = receivingDate;
    }
}
