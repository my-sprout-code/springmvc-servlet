package me.kwj1270.study.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String viewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> model() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
