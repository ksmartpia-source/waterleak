package com.waterleak.dao.reporting;

import com.waterleak.model.reporting.AckNbiot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AckNbiotRepository extends JpaRepository<AckNbiot, String> {
    List<AckNbiot> findAllByNbInstruction(String nbInstruction);
}
