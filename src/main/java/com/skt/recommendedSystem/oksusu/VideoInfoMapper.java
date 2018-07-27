package com.skt.recommendedSystem.oksusu;

import com.skt.recommendedSystem.oksusu.model.VideoInfoModel;
import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("videoInfoMapper")
public interface VideoInfoMapper {

	/**
	 * oksusu 시청 이력 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<VideoInfoModel> selectWatchVideoList(VideoInfoSearchModel filter) throws Exception;

	/**
	 * oksusu 추천 결과 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<VideoInfoModel> selectRecommendationVideoList(VideoInfoSearchModel filter) throws Exception;
}
