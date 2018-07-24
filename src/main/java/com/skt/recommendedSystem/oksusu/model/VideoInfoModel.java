package com.skt.recommendedSystem.oksusu.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("videoInfoModel")
public class VideoInfoModel {
	private String networkType;
	private String networkName;
	private String serviceId;
	private String servicePassword;
	private String serviceName;
	private String deviceId;
	private String deviceName;
	private String deviceSerial;
	private int period;
	private String description;
	private String createUser;
	private String updateUser;
	private String createDate;
	private String updateDate;
}
