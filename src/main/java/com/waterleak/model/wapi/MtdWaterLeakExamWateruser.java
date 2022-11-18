package com.waterleak.model.wapi;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Table(name = "MTD_NB_WATER_LEAK_EXAM_WATERUSER")
@Entity
public class MtdWaterLeakExamWateruser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "exam_wateruser_idx", length = 20)
    private Long examWateruserIdx;

    @ManyToOne
    @JoinColumn(name = "EXAM_GROUP_IDX")
    private MtdWaterLeakExamGroup examGroup;

    @Column(name = "CONSUMER_SID", unique = true)
    private Long consumerSid;

    @Column(name = "IMEI", unique = true, length = 50)
    private String imei;

    @Column(name = "EXAM_RESULT", nullable = true, length = 5)
    private String examResult;

    @Column(name = "CHANGE_STATUS", nullable = true, length = 5)
    private String changeStatus;

    @Column(name = "LEAK_MIN_USAGE", nullable = true, precision = 10, scale = 3)
    private BigDecimal leakMinUsage;

    @Column(name = "GROUP_SID")
    private Long groupSid;

    public void updateChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }
}




