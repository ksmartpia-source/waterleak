package com.waterleak.model.wapi;

import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@NoArgsConstructor
@Table(name = "MTD_NB_METERINFO_LEAK")
@IdClass(MtdMeterinfoLeakPK.class)
@Entity
public class MtdMeterinfoLeak implements Serializable {

    @Id
    @Column(name = "EXAM_WATERUSER_IDX")
    private Long examWateruserIdx;

    @Id
    @Column(name = "IMEI")
    private String imei;

    @Id
    @Column(name = "METERING_DATE")
    private Timestamp meteringDate;

    @Id
    @Column(name = "RECEIVING_DATE")
    private Timestamp receivingDate;

    @Column(name = "METERING_VALUE", precision = 10, scale = 3)
    private BigDecimal meteringValue;

    @Column(name = "METERING_USAGE", precision = 10, scale = 3)
    private BigDecimal meteringUsage;

    @Column(name = "METERING_STATE", length = 50)
    private String meteringState;

    @Column(name = "METERING_SIGNAL01", length = 11)
    private int meteringSignal01;

    @Column(name = "METERING_SIGNAL02", length = 11)
    private int meteringSignal02;

    @Column(name = "METERING_SIGNAL03", length = 11)
    private int meteringSignal03;

    @Column(name = "METERING_SIGNAL04", length = 11)
    private int meteringSignal04;

    @Column(name = "MODEM_RSSI", length = 11)
    private int modemRssi;

    @Column(name = "MODEM_SIGNAL01", length = 11)
    private int modemSignal01;

    @Column(name = "MODEM_SIGNAL02", length = 11)
    private int modemSignal02;

    @Column(name = "MODEM_SIGNAL03", length = 11)
    private int modemSignal03;

    @Column(name = "INFO_TAG", length = 25)
    private String infoTag;

    @Column(name = "ACUBE_SIGNAL01", length = 11)
    private int acubeSignal01;

    @Column(name = "ACUBE_SIGNAL02", length = 11)
    private int acubeSignal02;

    @Column(name = "CRITERION_DATE")
    private Date criterionDate;

    @Column(name = "GROUP_SID")
    private Long groupSid;

    @Builder
    public MtdMeterinfoLeak(Long examWateruserIdx, String imei, Timestamp meteringDate,
                            Timestamp receivingDate, BigDecimal meteringValue, BigDecimal meteringUsage,
                            String meteringState, int meteringSignal01, int meteringSignal02, int meteringSignal03,
                            int meteringSignal04, int modemRssi, int modemSignal01, int modemSignal02, int modemSignal03,
                            String infoTag, int acubeSignal01, int acubeSignal02, Date criterionDate,
                            Long groupSid) {
        this.examWateruserIdx = examWateruserIdx;
        this.imei = imei;
        this.meteringDate = meteringDate;
        this.receivingDate = receivingDate;
        this.meteringValue = meteringValue;
        this.meteringUsage = meteringUsage;
        this.meteringState = meteringState;
        this.meteringSignal01 = meteringSignal01;
        this.meteringSignal02 = meteringSignal02;
        this.meteringSignal03 = meteringSignal03;
        this.meteringSignal04 = meteringSignal04;
        this.modemRssi = modemRssi;
        this.modemSignal01 = modemSignal01;
        this.modemSignal02 = modemSignal02;
        this.modemSignal03 = modemSignal03;
        this.infoTag = infoTag;
        this.acubeSignal01 = acubeSignal01;
        this.acubeSignal02 = acubeSignal02;
        this.criterionDate = criterionDate;
        this.groupSid = groupSid;
    }
}