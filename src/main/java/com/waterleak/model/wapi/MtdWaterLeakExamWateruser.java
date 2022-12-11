package com.waterleak.model.wapi;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static com.waterleak.config.Globals.*;

@Getter
@NoArgsConstructor
@Table(name = "mtd_nb_water_leak_exam_wateruser")
@Entity
public class MtdWaterLeakExamWateruser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "exam_wateruser_idx", length = 20)
    private Long examWateruserIdx;

    @ManyToOne
    @JoinColumn(name = "exam_group_idx")
    private MtdWaterLeakExamGroup examGroup;

    @Column(name = "consumer_sid", unique = true)
    private Long consumerSid;

    @Column(name = "imei", unique = true, length = 50)
    private String imei;

    @Column(name = "exam_result", nullable = true, length = 5)
    private String examResult;

    @Column(name = "change_status", nullable = true, length = 5)
    private String changeStatus;

    @Column(name = "leak_min_usage", nullable = true, precision = 10, scale = 3)
    private BigDecimal leakMinUsage;

    @Column(name = "comm_type", nullable = true, length = 20)
    private String commType;

    @Column(name = "group_sid")
    private Long groupSid;

    public void updateChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    @Builder
    public MtdWaterLeakExamWateruser(
            Long examWateruserIdx,
            MtdWaterLeakExamGroup examGroup, Long consumerSid, String imei, String examResult,
            String changeStatus, BigDecimal leakMinUsage, Long groupSid, String commType
    ) {
        this.examWateruserIdx = examWateruserIdx;
        this.examGroup = examGroup;
        this.consumerSid = consumerSid;
        this.imei = imei;
        this.examResult = examResult;
        this.changeStatus = changeStatus;
        this.leakMinUsage = leakMinUsage;
        this.groupSid = groupSid;
        this.commType = commType;
    }

    public void determinResult(int leakCount, BigDecimal leakMinUsage) {
        this.leakMinUsage = leakMinUsage.multiply(BigDecimal.valueOf(6));
        if (leakCount <= RESULT_MIDDLE_COUNT) {
            this.examResult = WATERLEAK_RESULT_LEAK;
        } else if (leakCount <= RESULT_UPPER_COUNT) {
            this.examResult = WATERLEAK_RESULT_FINE_LEAK;
        } else {
            this.examResult = WATERLEAK_RESULT_NORMAL;
            this.leakMinUsage = BigDecimal.valueOf(0);
        }
    }
}
