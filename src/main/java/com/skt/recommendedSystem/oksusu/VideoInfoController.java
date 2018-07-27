package com.skt.recommendedSystem.oksusu;

import com.mobigen.framework.result.JsonResult;
import com.skt.recommendedSystem.oksusu.model.VideoInfoModel;
import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping(value = { "/oksusu" })
public class VideoInfoController {

	@Autowired
	private VideoInfoService videoInfo;
	
	/**
	 * oksusu 시청 이력 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWatchVideoList.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getWatchVideoList(VideoInfoSearchModel filter) throws Exception {
		log.info("### " + filter);
		JsonResult js = new JsonResult();
		List<VideoInfoModel> list = videoInfo.getWatchVideoList(filter);

		Map<String, Object> result = new HashMap<String,Object>();
		result.put("list", list);
		js.setData(result);
		return js;
	}

	/**
	 * oksusu 추천 결과 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRecommendationVideoList.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getRecommendationVideoList(VideoInfoSearchModel filter) throws Exception {
		log.info("### " + filter);
		JsonResult js = new JsonResult();
		List<VideoInfoModel> list = videoInfo.getRecommendationVideoList(filter);

		Map<String, Object> result = new HashMap<String,Object>();
		result.put("list", list);
		js.setData(result);
		return js;
	}
}
