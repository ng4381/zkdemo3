package org.example.sidepage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SidebarPageConfigAjaxbasedImpl implements SidebarPageConfig {
    HashMap<String, SidebarPage> pageMap = new LinkedHashMap<String, SidebarPage>();

    public SidebarPageConfigAjaxbasedImpl() {
        pageMap.put("fn1", new SidebarPage("fn1", "Главная", "img/home.png", "/home.zul"));
        pageMap.put("fn2", new SidebarPage("fn2", "Компании", "img/company.png", "/companies.zul"));
        pageMap.put("fn3", new SidebarPage("fn3", "Филиалы", "img/office.png", "/filials.zul"));
    }

    @Override
    public SidebarPage getPage(String name) {
        return null;
    }

    @Override
    public List<SidebarPage> getPages() {
        return pageMap.values().stream().collect(Collectors.toList());
    }
}
