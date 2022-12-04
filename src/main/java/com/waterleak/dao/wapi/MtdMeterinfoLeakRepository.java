package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.MtdWaterLeakExamGroup;
import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MtdMeterinfoLeakRepository extends JpaRepository<MtdMeterinfoLeak, MtdMeterinfoLeakPK> {
    List<MtdMeterinfoLeak> findAllByExamWateruserIdx(long examWateruserIdx);
}
