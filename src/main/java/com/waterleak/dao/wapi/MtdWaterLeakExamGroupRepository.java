package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MtdWaterLeakExamGroupRepository extends JpaRepository<MtdWaterLeakExamGroup, Long> {
    List<MtdWaterLeakExamGroup> findAllByExamStatus(String examStatus);

    List<MtdWaterLeakExamGroup> findAllByExamGroupIdxIn(List<Long> examGroupIdxList);
}
