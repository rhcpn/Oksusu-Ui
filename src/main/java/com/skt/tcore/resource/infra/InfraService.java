package com.skt.tcore.resource.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InfraService implements Infra {
    final String PATH_JSON_FILE_PATH = "temporary/";

    private String getFile(String filename) {
        String path = PATH_JSON_FILE_PATH + filename;
        Resource resource = new ClassPathResource(path);
        StringBuffer buf = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String content = buf.toString();
        log.debug("Load JSON =====\r\n" + content);
        return content;
    }

    private List<Object> getJsonToList(String filename) {
        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(filename + ".json"));
    }

    @Override
    public List<Object> getNavigation() {
        String filename = "navigation-tree";

        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(filename + ".json"));
    }

    @Override
    public List<Object> getDatacenter() {
        String filename = "datacenter";

        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(filename + ".json"));
    }

    @Override
    public List<Object> getFloor() {
        String filename = "floor";

        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(filename + ".json"));
    }

    @Override
    public List<Object> getRoom() {
        String filename = "room";

        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(filename + ".json"));
    }

    @Override
    public List<Object> getList(String parent, String type) {
        return getJsonToList(type);
    }

    public List<Object> getFilterList(String search, String type) {
        return getJsonToList(type);
    }

    public List<Object> getItemNameList(String type) {

        GsonJsonParser jsonParser = new GsonJsonParser();
        List<Object> list = jsonParser.parseList(getFile(type + "-itemname.json"));

        List<Object> resultList = new ArrayList<Object>();
        String[] displayNoneList = {"아이디", "타입", "위치정보"};
        for (int i=0 ; i < list.size(); i++){
            String name  = (String) list.get(i);

            InfraModel infraModel = new InfraModel();
            infraModel.setName(name);

            for(int j=0; j < displayNoneList.length; j++){
                if(name.equals(displayNoneList[j])){
                    infraModel.setHide(true);
                    break;
                }else{
                    infraModel.setHide(false);
                }
            }

            resultList.add(infraModel);
        }

        return resultList;
    }

    public List<Object> getTabList(String type){
        return getJsonToList(type);
    }

    @Override
    public List<Object> getFilters(String type) {
        return getJsonToList(type);
    }
}
