package com.waterleak.model.wapi;

import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
@Table(name = "mtd_nb_meterinfo_leak")
@IdClass(MtdMeterinfoLeakPK.class)
@Entity
public class MtdMeterinfoLeak implements Serializable {

	@Id
	@Column(name = "exam_wateruser_idx", length = 20)
	private Long examWateruserIdx;

	@Id
	@Column(name="imei", length = 50)
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
	
	@Column(name = "group_Sid")
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