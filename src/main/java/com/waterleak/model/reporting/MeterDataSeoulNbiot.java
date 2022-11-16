package com.waterleak.model.reporting;

import com.waterleak.model.reporting.id.MeterDataSeoulNbiotPK;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@IdClass(MeterDataSeoulNbiotPK.class)
@Table(name = "meterdataseoulnbiot")
@Entity
public class MeterDataSeoulNbiot implements Serializable {
    @Id
    @Column(name = "IMEI", unique=true, length = 50)
    private String imei;

    @Id
    @Column(name = "METERING_DATE")
    private Timestamp meteringDate;

    @Column(name = "METERING_VALUE")
    private Double meteringValue;
}
