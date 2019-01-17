package com.skt.recommendedSystem.oksusu;

import com.skt.recommendedSystem.oksusu.model.VideoInfoModel;
import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.io.File;

@Slf4j
@Service
public class VideoInfoService {

	@Value("${config.img-path}")
	private String IMG;

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

		// return resultModel;
		return getImage(0, resultModel);
	}

	/**
	 * oksusu 추천 결과 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<VideoInfoModel> getRecommendationVideoList(VideoInfoSearchModel filter) throws Exception {
		List<VideoInfoModel> resultModel = videoInfo.selectRecommendationVideoList(filter);

		// return resultModel;
		return getImage(1, resultModel);
	}

	/**
	 *
	 * @param flag (0 == list용 이미지(173x96), 1 == phone용 이미지(368x205))
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<VideoInfoModel> getImage(int flag, List params) throws Exception {
		List<VideoInfoModel> resultModel = params ;

		// String folderName = null;
		String videoId = null;
		String videoImgId = null;

		// folderName = (flag == 0 ? "sequence/" : "candidates/");

		if (resultModel.size() > 0) {
			for (int i = 0; i < resultModel.size(); i++) {
				videoId = resultModel.get(i).getVideoId();
				// videoImgId = IMG + folderName + "[thum]" + videoId + ".jpg";
				// File imgName = new File(videoImgId);

				//if (imgName.exists()) {
					if (flag == 0) {
						resultModel.get(i).setImgListSrc(IMG + "sequence/[thum]" + videoId + ".jpg");
					}else {
						resultModel.get(i).setImgListSrc(IMG + "candidates/[thum]" + videoId + ".jpg");
						resultModel.get(i).setImgPhoneSrc(IMG + "candidates/[thum]" + videoId + ".jpg");
					}
				/*} else {
					if (flag == 0) {
						resultModel.get(i).setImgListSrc(IMG + "sequence_no_img.jpg");
					}else {
						resultModel.get(i).setImgListSrc(IMG + "sequence_no_img.jpg");
						resultModel.get(i).setImgPhoneSrc(IMG + "candidates_no_img.jpg");
					}
				}*/
			}
		}

		return resultModel;
	}
}
