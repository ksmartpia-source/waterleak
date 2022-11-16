package com.waterleak.dao.reporting;

import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.reporting.id.MeterDataSeoulNbiotPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterDataSeoulNbiotRepository extends JpaRepository<MeterDataSeoulNbiot, MeterDataSeoulNbiotPK> {
}
