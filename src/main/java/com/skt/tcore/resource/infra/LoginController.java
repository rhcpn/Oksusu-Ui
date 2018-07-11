package com.skt.tcore.resource.infra;

import com.mobigen.framework.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @RequestMapping(value = "/loginByUsername.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult loginByUsername(String name) {

        JsonResult js = new JsonResult();
        Map resultMap = new LinkedHashMap<String, Object>();
        if(name.equals("admin")){
            resultMap.put("token", "admin1234");
        }else if(name.equals("user")){
            resultMap.put("token", "user1234");
        }
        js.setData(resultMap);

        return js;
    }

    @RequestMapping(value = "/getUserInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserInfo(String token) {

        JsonResult js = new JsonResult();
        Map resultMap = new LinkedHashMap<String, Object>();
        if(token.equals("admin1234")){
            resultMap.put("name", "tcoreadmin");
            List<String> roles = new ArrayList<String>();
            roles.add("admin");
            resultMap.put("roles", roles);
            resultMap.put("token", "admin1234");
        }else if(token.equals("user1234")){
            resultMap.put("name", "tcoreuser");
            List<String> roles = new ArrayList<String>();
            roles.add("user");
            resultMap.put("roles", roles);
            resultMap.put("token", "user1234");
        }

        js.setData(resultMap);

        return js;
    }

    @RequestMapping(value = "/logout.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult logout() {
        JsonResult js = new JsonResult();
        js.setData("success");
        return js;
    }
}
