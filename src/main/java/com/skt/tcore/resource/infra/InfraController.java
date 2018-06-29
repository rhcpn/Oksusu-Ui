package com.skt.tcore.resource.infra;

import com.mobigen.framework.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/resource")
public class InfraController {
    @Autowired
    private InfraService infraService;

    @RequestMapping(value = "/infra/list.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult list(String parent, String type) {
        JsonResult js = new JsonResult();
        js.setData(infraService.getList(parent, type));
        return js;
    }
}