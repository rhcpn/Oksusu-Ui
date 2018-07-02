package com.skt.tcore.resource.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(resource.getFile().getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public List<Object> getItemNameList(String type) {

        GsonJsonParser jsonParser = new GsonJsonParser();
        return jsonParser.parseList(getFile(type + "-itemname.json"));
    }

    @Override
    public List<Object> getFilters() {
        return null;
    }
}
