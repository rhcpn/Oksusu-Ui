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
     *   hardware list
     * */
    @RequestMapping(value = "/infra/list.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(String parent, String type) {
        JsonResult js = new JsonResult();

        Map resultMap = new HashMap();
        resultMap.put("hardwareList", infraService.getList(parent, type));

        if (type != null && !type.equals("")) {
            switch (type) {
                case "blade-server":
                    String[] bladeserverNames = {"아이디", "장비명", "장비높이", "홀번호", "상태", "제조사", "모델", "시리얼 번호", "서비스 군", "서비스 명", "장비무게(Kg)", "발열량(Btu/Hr)", "전원수량(개)",
                            "장비전원(W)", "Host명", "CPU프로세서수(CPU)", "CPU전체 코어수(Core)", "Memory 갯수", "Memory 용량(GB)", "Disk 갯수", "Disk 용량(GB)", "서비스 대표 IP", "Port IP", "OS 명",
                            "OS 버전", "입고일", "폐기 예정일", "메모", "CPU모델명", "프로세서 당 코어 수", "담당자", "타입"};
                    resultMap.put("itemNameList", bladeserverNames);
                    break;
                case "enclosure":
                    String[] enclosureNames = {"아이디", "장비명", "장비높이", "홀번호", "가로크기", "세로크기", "상태", "제조사", "모델", "시리얼 번호", "서비스 군", "서비스 명", "타입"};
                    resultMap.put("itemNameList", enclosureNames);
                    break;
                case "etc":
                    //etc-it
                    String[] etcNames = {"아이디", "장비명", "장비높이", "홀번호", "상태", "제조사", "모델", "시리얼 번호", "서비스 군", "서비스 명", "타입", "장비무게(Kg)", "발열량(Btu/Hr)", "전원수량(개)"
                            , "장비전원(W)", "Host명", "입고일", "폐기 예정일", "메모", "담당자", "타입"};
                    resultMap.put("itemNameList", etcNames);
                    break;
                case "etc-facility":
                    String[] facilityNames = {"아이디", "장비명", "가로크기", "세로크기", "위치정보", "상태", "최대 전력량(kw)", "제조사", "모델", "폐기 예정일", "메모", "타입"};
                    resultMap.put("itemNameList", facilityNames);
                    break;
                case "havc":
                    String[] havcNames = {"아이디", "항온항습기 명", "항온항습기 타입", "가로크기", "세로크기", "위치정보", "상태", "최대 전력량(kw)", "제조사", "모델", "폐기 예정일", "메모", "타입"};
                    resultMap.put("itemNameList", havcNames);
                    break;
                case "pdu":
                    String[] pduNames = {"아이디", "분전반 명", "가로크기", "세로크기", "위치정보", "상태", "최대 전력량(kw)", "제조사", "모델", "폐기 예정일", "메모", "타입"};
                    resultMap.put("itemNameList", pduNames);
                    break;
                case "rack":
                    String[] rackNames = {"아이디", "Rack 명", "전체 Unit", "가로크기", "세로크기", "위치정보", "상태", "최대 전력량(kw)", "무게 (Kg)", "제조사", "모델", "폐기 예정일", "메모", "타입"};
                    resultMap.put("itemNameList", rackNames);
                    break;
                case "storage":
                    String[] storageNames = {"아이디", "장비명", "장비높이", "홀번호", "상태", "제조사", "모델", "시리얼 번호", "서비스 군", "서비스 명", "장비무게(Kg)", "발열량(Btu/Hr)", "전원수량(개)", "장비전원(W)",
                            "Host명", "Disk 갯수", "Disk 용량(GB)", "입고일", "폐기예정일", "메모", "연결된 장비명", "연결된 장비 포트", "담당자", "타입"};
                    resultMap.put("itemNameList", storageNames);
                    break;
                case "switch":
                    String[] switchNames = {"아이디", "장비명", "장비높이", "홀번호", "상태", "제조사", "상태", "모델", "시리얼 번호", "서비스 군", "서비스 명", "장비무게(Kg)", "발열량(Btu/Hr)", "전원수량(개)",
                            "장비전원(W)", "Host명", "입고일", "폐기 예정일", "메모", "담당자", "타입"};
                    resultMap.put("itemNameList", switchNames);
                    break;
                case "ups":
                    String[] upsNames = {"아이디", "UPS 명", "장비넓이", "가로크기", "세로크기", "위치정보", "상태", "최대 전력량(kw)", "전원사용 총량(W)", "제조사", "모델", "폐기 예정일", "메모", "타입"};
                    resultMap.put("itemNameList", upsNames);
                    break;
                default:
                    // bm-server
                    String[] bmserverNames = {"아이디", "장비명", "장비높이", "홀번호", "상태", "제조사", "모델", "시리얼 번호", "서비스 군", "서비스 명", "장비무게(Kg)", "발열량(Btu/Hr)", "전원수량(개)",
                            "장비전원(W)", "Host명", "CPU프로세서수(CPU)", "CPU전체 코어수(Core)", "Memory 갯수", "Memory 용량(GB)", "Disk 갯수", "Disk 용량(GB)", "서비스 대표 IP", "Port IP", "OS 명",
                            "OS 버전", "입고일", "폐기 예정일", "메모", "CPU모델명", "프로세서 당 코어 수", "담당자", "타입"};
                    resultMap.put("itemNameList", bmserverNames);
                    break;
            }
        }

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

        String[] itemNames = {"아이디", "데이터 센터 명", "가용상면 갯수", "사용상면 갯수", "가용상면 갯수", "서버 (BM + Blade) 갯수", "스토리지 갯수", "스위치 갯수", "기타 갯수", "온도", "습도", "전력", "타입"};
        resultMap.put("itemNameList", itemNames);

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

        String[] itemNames = {"아이디", "데이터 센터 명", "가용상면 갯수", "사용상면 갯수", "가용상면 갯수", "서버 (BM + Blade) 갯수", "스토리지 갯수", "스위치 갯수", "기타 갯수", "온도", "습도", "전력", "타입"};
        resultMap.put("itemNameList", itemNames);

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

        String[] itemNames = {"아이디", "데이터 센터 명", "가용상면 갯수", "사용상면 갯수", "가용상면 갯수", "서버 (BM + Blade) 갯수", "스토리지 갯수", "스위치 갯수", "기타 갯수", "온도", "습도", "전력", "타입"};
        resultMap.put("itemNameList", itemNames);

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
        String[] itemNames = {"rack", "ups", "pdu", "hvac", "etc-facility", "enclosure", "bm-server", "blade-server", "switch", "storage", "etc-it"};
        resultMap.put("tabList", itemNames);

        js.setData(resultMap);

        return js;
    }

    /*
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
