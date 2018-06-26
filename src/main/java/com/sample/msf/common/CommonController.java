package com.sample.msf.common;

import com.mobigen.framework.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;

	/**
	 * 메뉴조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMenu.json")
	@ResponseBody
	public JsonResult getMenu() throws Exception {
		JsonResult js = new JsonResult();
		js.setData(commonService.getMenu());
		return js;
	}
}
