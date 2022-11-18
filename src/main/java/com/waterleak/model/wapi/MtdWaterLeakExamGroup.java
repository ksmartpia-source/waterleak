package com.waterleak.model.wapi;

import static com.waterleak.config.Globals.WATERLEAK_STATUS_START;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor
@Table(name = "MTD_NB_WATER_LEAK_EXAM_GROUP")
@Entity
public class MtdWaterLeakExamGroup {
	@Id
	@Column(name = "EXAM_GROUP_IDX", length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long examGroupIdx;
	
	@OneToMany(mappedBy = "examGroup")
	private List<MtdWaterLeakExamWateruser> leakWaterUsers = new ArrayList<MtdWaterLeakExamWateruser>();

	@Column(name = "EXAM_NM", length = 200)
	private String examNm;
	
	@Column(name = "EXAM_PLAN_START_DT")
	private Timestamp examPlanStartDt;
	
	@Column(name = "EXAM_STARTED_DT", nullable = true)
	private Timestamp examStartedDt;
	
	@Column(name = "EXAM_FINISHED_DT", nullable = true)
	private Timestamp examFinishiedDt;
	
	@Column(name = "EXAM_STATUS", length = 10)
	private String examStatus;
	
	@Column(name = "CREATED_USER", length = 20)
	private String createdUser;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "GROUP_SID")
	private Long groupSid;

	public MtdWaterLeakExamGroup changeGroupStatusWithStart() {
		this.examStatus = WATERLEAK_STATUS_START;
		this.examStartedDt = new Timestamp(System.currentTimeMillis());
		this.examFinishiedDt = Timestamp.valueOf(LocalDate.now().plusDays(3).atStartOfDay());
		return this;
	}
}
