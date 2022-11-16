package com.waterleak.model.reporting.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeterDataSeoulNbiotPK implements Serializable {
    private String imei;
    private Timestamp meteringDate;
}
