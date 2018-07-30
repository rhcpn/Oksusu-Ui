package com.skt.recommendedSystem.oksusu;

import com.skt.recommendedSystem.oksusu.model.VideoInfoModel;
import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class VideoInfoService {

	@Autowired
	private VideoInfoMapper videoInfo;

    /**
     * oksusu 시청 이력 정보 조회
     * @param filter
     * @return
     * @throws Exception
     */
	public List<VideoInfoModel> getWatchVideoList(VideoInfoSearchModel filter) throws Exception {
		List<VideoInfoModel> resultModel = videoInfo.selectWatchVideoList(filter);
		/*for (int i = 0; i < resultModel.size(); i++) {
			log.debug(resultModel.get(i).getVideoSvcId());
			String contentsId = resultModel.get(i).getVideoSvcId();
			//resultModel[i].add('imgUrl', this.imageRestCall('left', contentsId))
		}*/

		return resultModel;
	}

	/**
	 * oksusu 추천 결과 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<VideoInfoModel> getRecommendationVideoList(VideoInfoSearchModel filter) throws Exception {
		List<VideoInfoModel> resultModel = videoInfo.selectRecommendationVideoList(filter);
		return resultModel;
	}
}
