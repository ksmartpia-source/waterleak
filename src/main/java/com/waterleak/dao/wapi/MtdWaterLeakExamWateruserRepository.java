package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MtdWaterLeakExamWateruserRepository extends JpaRepository<MtdWaterLeakExamWateruser, Long> {
    List<MtdWaterLeakExamWateruser> findAllByExamGroup(MtdWaterLeakExamGroup group);
}
