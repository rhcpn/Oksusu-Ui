package com.skt.tcore.resource.infra;

import com.google.gson.Gson;
import com.mobigen.framework.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/resource")
public class InfraController {
    @Autowired
    private InfraService infraService;

    /*

        //
     *   hardware list
     * */
    @RequestMapping(value = "/infra/list.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(String parent, String type) {
        JsonResult js = new JsonResult();

        //default - bm-server
        if(type == null || type.equals("")){
            type = "bm-server";
        }

        Map resultMap = new HashMap();
        resultMap.put("hardwareList", infraService.getList(parent, type));
        resultMap.put("itemNameList", infraService.getItemNameList(type));

        js.setData(resultMap);

        return js;
    }

    /*
     *   navigation tree
     * */
    @RequestMapping(value = "/infra/navigation.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult navigation() {
        JsonResult js = new JsonResult();
        js.setData(infraService.getNavigation());
        return js;
    }

    /*
     *   datacenter list
     * */
    @RequestMapping(value = "/infra/datacenter.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult datacneter() {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        resultMap.put("datacenterList", infraService.getDatacenter());
        resultMap.put("itemNameList", infraService.getItemNameList("datacenter"));

        js.setData(resultMap);
        return js;
    }

    /*
     *   floor list
     * */
    @RequestMapping(value = "/infra/floor.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult floor() {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        resultMap.put("floorList", infraService.getFloor());
        resultMap.put("itemNameList", infraService.getItemNameList("floor"));

        js.setData(resultMap);
        return js;
    }

    /*
     *   room list
     * */
    @RequestMapping(value = "/infra/room.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult room() {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        resultMap.put("roomList", infraService.getRoom());
        resultMap.put("itemNameList", infraService.getItemNameList("room"));

        js.setData(resultMap);

        return js;
    }

    /*
     *   tab list
     * */
    @RequestMapping(value = "/infra/tabList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult tabList() {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        resultMap.put("roomTabList", infraService.getItemNameList("tab"));
        resultMap.put("rackTabList", infraService.getItemNameList("rackTab"));

        js.setData(resultMap);

        return js;
    }

    /*
         필터 검색 항목들
     *   Filter Seach Item List
     * */
    @RequestMapping(value = "/infra/filterSearchItemList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult filterSearchItemList() {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        String[] itemNames = {"장비명", "Host명", "타입", "상태", "서비스 대표 IP", "담당자", "서비스 명", "시리얼 번호", "메모"};
        String[] itemType = {"name", "hostName", "type", "status", "mainIpAddr", "operatorName", "serviceName", "serialNumber", "remark"};
        resultMap.put("filter-hw-field", itemNames);

        js.setData(resultMap);

        return js;
    }
}
