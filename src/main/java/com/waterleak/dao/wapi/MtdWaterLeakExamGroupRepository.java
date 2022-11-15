package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtdWaterLeakExamGroupRepository extends JpaRepository<MtdWaterLeakExamGroup, Long> {
}
