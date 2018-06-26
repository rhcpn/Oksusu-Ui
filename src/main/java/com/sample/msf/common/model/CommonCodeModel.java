package com.sample.msf.common.model;

import java.util.List;

import lombok.Data;

@Data
public class CommonCodeModel {
	
	private String topCode;
	private String subCode;
	private String codeName;
	private String codeValue;
	private String desc;

	private String[] subCodeList;
}
