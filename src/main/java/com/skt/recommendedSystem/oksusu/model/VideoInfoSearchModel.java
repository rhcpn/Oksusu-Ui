package com.skt.recommendedSystem.oksusu.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("videoInfoSearchModel")
public class VideoInfoSearchModel {
	private int pageIndex;
	private int pageCount;
	private String order;
	private String exp;
	
	private String userId;

	
	private List<String> networkTypeList;
	private List<String> serviceIdList;
	private List<String> servicePasswordList;
	private List<String> deviceIdList;
	private List<String> deviceSerialList;
	
	private String deleteStringList;

}
