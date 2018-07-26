package com.skt.recommendedSystem.oksusu;

import com.mobigen.framework.result.JsonResult;
import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = { "/oksusu" })
public class VideoInfoController {

	@Autowired
	private VideoInfoService videoInfo;
	
	/**
	 * oksusu 정보 조회
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWatchVideoList.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getWatchVideoList(VideoInfoSearchModel filter) throws Exception {
		log.info("### " + filter);
		JsonResult js = new JsonResult();
		js.setData(videoInfo.getWatchVideoList(filter));
		return js;
	}
}
