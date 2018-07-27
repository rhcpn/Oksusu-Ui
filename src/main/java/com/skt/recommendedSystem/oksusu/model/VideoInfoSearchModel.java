package com.skt.recommendedSystem.oksusu.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("videoInfoSearchModel")
public class VideoInfoSearchModel {
	private String userId;
	private String userSvnId;
	
	private List<String> networkTypeList;
	private List<String> serviceIdList;
	private List<String> servicePasswordList;
	private List<String> deviceIdList;
	private List<String> deviceSerialList;
	
}
