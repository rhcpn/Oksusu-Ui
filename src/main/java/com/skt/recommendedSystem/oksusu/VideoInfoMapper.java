package com.skt.recommendedSystem.oksusu;

import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("videoInfoMapper")
public interface VideoInfoMapper {

	/**
	 * oksusu 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectWatchVideoList(VideoInfoSearchModel filter) throws Exception;
}
