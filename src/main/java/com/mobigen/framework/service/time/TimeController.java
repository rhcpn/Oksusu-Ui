package com.mobigen.framework.service.time;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobigen.framework.result.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/service/time")
public class TimeController {
	
	@Autowired
	private TimeService timeService;
	
	@RequestMapping(value = "/servertime.json")
	@ResponseBody
	public JsonResult serverTime() throws Exception {
		JsonResult js = new JsonResult();
		js.setData(timeService.getServerTime());
		log.debug("CURRENT SYSTEM TIME: " + js.getData().toString());
		return js;
	}
	
	@RequestMapping(value = "/timeoffset.json")
	@ApiOperation("Get Server Time Offest")
	@ResponseBody
	public JsonResult timeOffset(String clientDateTime) {
		JsonResult js = new JsonResult();
		js.setData(timeService.getTimeOffset(clientDateTime));
		log.debug("CURRENT SYSTEM TIME OFFSET: " + js.getData().toString());
		return js;
	}
	
}
