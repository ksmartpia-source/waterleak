package com.waterleak.dao.reporting;

import com.waterleak.model.reporting.MeterDataSeoulNbiot;
import com.waterleak.model.reporting.id.MeterDataSeoulNbiotPK;
import java.util.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MeterDataSeoulNbiotRepository extends JpaRepository<MeterDataSeoulNbiot, MeterDataSeoulNbiotPK> {
    List<MeterDataSeoulNbiot> findTop10ByImeiOrderByMeteringDateAsc(String imei);
    List<MeterDataSeoulNbiot> findAllByImeiAndMeteringDateBetweenOrderByMeteringDateDesc(String imei, Timestamp fromDt, Timestamp toDt);
}
