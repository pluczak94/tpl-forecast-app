package com.luczak.tcp.weatherapp.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        LOGGER.info("[" + request.getMethod() + "]" + request.getRequestURI() + "Parameters: latitude: " + request.getParameter("latitude") + " longitude: " + request.getParameter("longitude"));
        return true;
    }

}