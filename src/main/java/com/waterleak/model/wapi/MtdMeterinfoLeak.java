package com.waterleak.model.wapi;

import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
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
	@Column(name = "exam_wateruser_idx", length = 20)
	private Long examWateruserIdx;

	@Id
	@Column(name="IMEI", length = 50)
	private String imei;

	@Id
	@Column(name="metering_date")
	private Timestamp meteringDate;

	@Id
	@Column (name = "receiving_date")
	private Timestamp receivingDate;
	
	@Column(name = "metering_value", precision = 10, scale = 3)
	private BigDecimal meteringValue;
	
	@Column(name = "metering_usage", precision = 10, scale = 3)
	private BigDecimal meteringUsage;
	
	@Column(name = "metering_state", length = 50)
	private String meteringState;
	
	@Column(name = "metering_signal01", length = 11)
	private int meteringSignal01;
	
	@Column(name = "metering_signal02", length = 11)
	private int meteringSignal02;
	
	@Column(name = "metering_signal03", length = 11)
	private int meteringSignal03;
	
	@Column(name = "metering_signal04", length = 11)
	private int meteringSignal04;
	
	@Column(name = "modem_rssi", length = 11)
	private int modemRssi;
	
	@Column(name = "modem_signal01", length = 11)
	private int modemSignal01;
	
	@Column(name = "modem_signal02", length = 11)
	private int modemSignal02;
	
	@Column(name = "modem_signal03", length = 11)
	private int modemSignal03;
	
	@Column(name = "info_tag", length = 25)
	private String infoTag;
	
	@Column(name = "acube_signal01", length = 11)
	private int acubeSignal01;
	
	@Column(name = "acube_signal02", length = 11)
	private int acubeSignal02;
	
	@Column(name = "criterion_date")
	private Date criterionDate;
	
	@Column(name = "GROUP_SID")
	private Long groupSid;
}