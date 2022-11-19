package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtdWaterLeakExamWateruserRepository extends JpaRepository<MtdWaterLeakExamWateruser, Long> {
    List<MtdWaterLeakExamWateruser> findAllByExamGroup(MtdWaterLeakExamGroup group);
}
