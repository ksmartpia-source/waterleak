package com.waterleak.model.reporting;

import com.waterleak.model.reporting.id.MeterDataSeoulNbiotPK;
import java.io.Serializable;
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
    @Column(name = "IMEI", length = 50)
    private String imei;

    @Id
    @Column(name = "METERING_DATE")
    private Timestamp meteringDate;

    @Column(name = "IMSI", length = 50)
    private String imsi;

    @Column(name = "METERING_VALUE")
    private Double meteringValue;

    @Builder
    public MeterDataSeoulNbiot(String imei, Timestamp meteringDate, String imsi,
        Double meteringValue) {
        this.imei = imei;
        this.meteringDate = meteringDate;
        this.imsi = imsi;
        this.meteringValue = meteringValue;
    }
}
