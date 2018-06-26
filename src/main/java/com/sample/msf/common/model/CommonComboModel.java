package com.sample.msf.common.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("CommonComboModel")
public class CommonComboModel {
	private String label;
	private String value;
}
