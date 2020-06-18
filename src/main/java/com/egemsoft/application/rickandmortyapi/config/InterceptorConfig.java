package com.egemsoft.application.rickandmortyapi.config;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.logger.LoggerKeys;
import com.egemsoft.application.rickandmortyapi.model.HeaderKeys;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class InterceptorConfig extends HandlerInterceptorAdapter {
    private static final ESLogger logger = ESLogger.getLogger(InterceptorConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        logger.trace("InterceptorConfig.preHandle start");

        MDC.put(LoggerKeys.OPERATION, request.getRequestURI());
        MDC.put(LoggerKeys.USER_IP, request.getHeader(HeaderKeys.HEADER_USER_IP));
        MDC.put(LoggerKeys.USER_AGENT, request.getHeader(HeaderKeys.HEADER_USER_AGENT));

        String requestId = request.getHeader(HeaderKeys.HEADER_REQUEST_ID);
        MDC.put(LoggerKeys.REQUEST_ID, requestId != null ? requestId : UUID.randomUUID().toString());

        logger.trace("InterceptorConfig.preHandle end");

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        MDC.clear();

        super.postHandle(request, response, handler, modelAndView);
    }
}
