package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.MtdWaterLeakExamWateruser;
import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtdMeterinfoLeakRepository extends JpaRepository<MtdMeterinfoLeak, MtdMeterinfoLeakPK> {

}
