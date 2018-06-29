package com.skt.tcore.resource.infra;

import java.util.List;

public interface Infra {
    List<Object>getNavigation();
    List<Object>getList(String parent, String type);
    List<Object>getTabs(String parent, String type);
    List<Object>getFilters();
}
