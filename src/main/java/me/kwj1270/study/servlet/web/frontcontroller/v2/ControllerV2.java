package me.kwj1270.study.servlet.web.frontcontroller.v2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException;
}
