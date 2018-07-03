package com.skt.tcore.resource.infra;

import com.mobigen.framework.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/resource")
public class InfraController {
    @Autowired
    private InfraService infraService;

    /*
     *   hardware list
     * */
    @RequestMapping(value = "/infra/list.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(String parent, String type) {
        JsonResult js = new JsonResult();

        //default - bm-server
        if (type == null || type.equals("")) {
            type = "bm-server";
        }

        Map resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("hardwareList", infraService.getList(parent, type));
        resultMap.put("itemNameList", infraService.getItemNameList(type));

        js.setData(resultMap);

        return js;
    }


    /*
     *   filter serach List
     * */
    @RequestMapping(value = "/infra/filterList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult filterList(String search, String type) {
        JsonResult js = new JsonResult();

        //default - bm-server
        if (type == null || type.equals("")) {
            type = "bm-server";
        }

        Map resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("filterList", infraService.getFilterList(search, "filter-search"));
        resultMap.put("itemNameList", infraService.getItemNameList("bm-server"));

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

        Map resultMap = new LinkedHashMap<String, Object>();
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

        Map resultMap = new LinkedHashMap<String, Object>();
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

        Map resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("roomList", infraService.getRoom());
        resultMap.put("itemNameList", infraService.getItemNameList("room"));

        js.setData(resultMap);

        return js;
    }

    /*
     *   room tab list
     * */
    @RequestMapping(value = "/infra/roomTabList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult roomTabList() {
        JsonResult js = new JsonResult();

        Map resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("roomTabList", infraService.getTabList("roomTab"));

        js.setData(resultMap);

        return js;
    }

    /*
     *   rack tab list
     * */
    @RequestMapping(value = "/infra/rackTabList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult rackTabList() {
        JsonResult js = new JsonResult();

        Map resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("rackTabList", infraService.getTabList("rackTab"));

        js.setData(resultMap);

        return js;
    }

    /*
         필터 검색 항목들
     *   Filter Item List
     * */
    @RequestMapping(value = "/infra/filterItemList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult filterSearchItemList() {
        JsonResult js = new JsonResult();

        Map resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("filterHwFieldList", infraService.getFilters("filter-hw-field"));
        resultMap.put("filterHwStatusList", infraService.getFilters("filter-hw-status"));
        resultMap.put("filterHwTypeList", infraService.getFilters("filter-hw-type"));

        js.setData(resultMap);

        return js;
    }
}
