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

    /*
     *   hardware list
     * */
    @RequestMapping(value = "/infra/list.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult list(String parent, String type) {
        JsonResult js = new JsonResult();
        js.setData(infraService.getList(parent, type));
        return js;
    }

    /*
    *   navigation tree
    * */
    @RequestMapping(value = "/infra/navigation.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult navigation() {
        JsonResult js = new JsonResult();
        js.setData(infraService.getNavigation());
        return js;
    }

    /*
    *   datacenter list
    * */
    @RequestMapping(value = "/infra/datacenter.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult datacneter() {
        JsonResult js = new JsonResult();
        js.setData(infraService.getDatacenter());
        return js;
    }

    /*
    *   floor list
    * */
    @RequestMapping(value = "/infra/floor.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult floor() {
        JsonResult js = new JsonResult();
        js.setData(infraService.getFloor());
        return js;
    }

    /*
    *   room list
    * */
    @RequestMapping(value = "/infra/room.json", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult room() {
        JsonResult js = new JsonResult();
        js.setData(infraService.getRoom());
        return js;
    }
}
