package com.skt.recommendedSystem.oksusu.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("videoInfoModel")
public class VideoInfoModel {
	private String userId;
	private String orderNo;
	private String videoId;
	private String videoType;
	private String videoSvcId;
	private String title;
	private String ddShowing;
	private String dtTelevise;
	private String watchDate;
	private String watchTime;
	private String score;
	private String metaTypNm;
	private String imgListSrc;
	private String imgPhoneSrc;
}
