package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.MtdMeterinfoLeak;
import com.waterleak.model.wapi.id.MtdMeterinfoLeakPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtdMeterinfoLeakRepository extends JpaRepository<MtdMeterinfoLeak, MtdMeterinfoLeakPK> {
}
