package com.waterleak.model.wapi.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MtdMeterinfoLeakPK implements Serializable {
	private Long examWateruserIdx;
	private String imei;
	private Timestamp meteringDate;
	private Timestamp receivingDate;
}