package me.kwj1270.study.servlet.web.frontcontroller.v5;

import me.kwj1270.study.servlet.web.frontcontroller.ModelView;
import me.kwj1270.study.servlet.web.frontcontroller.MyView;
import me.kwj1270.study.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import me.kwj1270.study.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import me.kwj1270.study.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import me.kwj1270.study.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import me.kwj1270.study.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import me.kwj1270.study.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import me.kwj1270.study.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import me.kwj1270.study.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMapping();
        initHandlerAdapter();
    }

    private void initHandlerMapping() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

    }

    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = handler(request);
        if (handler == null) {
            response.setStatus(SC_NOT_FOUND);
        }
        MyHandlerAdapter handlerAdapter = handlerAdapter(handler);
        ModelView modelView = handlerAdapter.handle(request, response, handler);
        MyView myView = viewResolver(modelView.viewName());
        myView.render(modelView.model(), request, response);
    }

    private Object handler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter handlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
