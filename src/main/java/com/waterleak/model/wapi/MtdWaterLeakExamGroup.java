package com.waterleak.model.wapi;

import static com.waterleak.config.Globals.WATERLEAK_STATUS_FINISH;
import static com.waterleak.config.Globals.WATERLEAK_STATUS_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	private LocalDateTime examPlanStartDt;
	
	@Column(name = "EXAM_STARTED_DT", nullable = true)
	private LocalDateTime examStartedDt;
	
	@Column(name = "EXAM_FINISHED_DT", nullable = true)
	private LocalDateTime examFinishiedDt;
	
	@Column(name = "EXAM_STATUS", length = 10)
	private String examStatus;
	
	@Column(name = "CREATED_USER", length = 20)
	private String createdUser;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "GROUP_SID")
	private Long groupSid;

	public void updateExamPlanStartDt(LocalDateTime examPlanStartDt) {
		this.examPlanStartDt = examPlanStartDt;
	}

	public void startExam() {
		this.examStatus = WATERLEAK_STATUS_START;
		this.examStartedDt = LocalDateTime.now();
		this.examFinishiedDt = LocalDateTime.now().plusDays(3);
	}

	public void failExam() {
		this.examStatus = WATERLEAK_STATUS_FINISH;
		this.examStartedDt = LocalDateTime.now();
		this.examFinishiedDt = LocalDateTime.now();
	}

	@Builder
	public MtdWaterLeakExamGroup(Long examGroupIdx,
			List<MtdWaterLeakExamWateruser> leakWaterUsers, String examNm,
			LocalDateTime examPlanStartDt, LocalDateTime examStartedDt,
			LocalDateTime examFinishiedDt, String examStatus, String createdUser,
			Timestamp createdDate, Long groupSid) {
		this.examGroupIdx = examGroupIdx;
		this.leakWaterUsers = leakWaterUsers;
		this.examNm = examNm;
		this.examPlanStartDt = examPlanStartDt;
		this.examStartedDt = examStartedDt;
		this.examFinishiedDt = examFinishiedDt;
		this.examStatus = examStatus;
		this.createdUser = createdUser;
		this.createdDate = createdDate;
		this.groupSid = groupSid;
	}
}
