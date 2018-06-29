package com.skt.tcore.resource.infra;

import java.util.List;

public interface Infra {
    List<Object>getNavigation();
    List<Object>getDatacenter();
    List<Object>getFloor();
    List<Object>getRoom();
    List<Object>getList(String parent, String type);
    List<Object>getTabs(String parent, String type);
    List<Object>getFilters();
}
