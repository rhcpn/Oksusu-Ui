package com.skt.recommendedSystem.oksusu.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("videoInfoSearchModel")
public class VideoInfoSearchModel {
	private String userSvnId;
}
