package com.nethru.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * CORS 를 위한 인터셉터.
 * Origin 헤더가 request 에 존재할 경우, CORS 에 필요한 response 헤더를 추가한다.
 * 즉 크로스도메인 요청인 경우, 브라우저에서 이 요청을 정상적으로 처리할 수 있도록 Access-Control 관련 헤더를 붙여준다.
 * @author mj
 *
 */
public class CorsInterceptor implements HandlerInterceptor
{
    private static final Logger logger = LoggerFactory.getLogger(CorsInterceptor.class);
    
    private static final String  ORIGIN                           = "Origin";
    private static final String  ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
    private static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        logger.debug("preHandle() start");
        
        String origin = request.getHeader(ORIGIN);
        
        logger.debug("Origin Header: {}", origin);
        
        if (StringUtils.hasLength(origin))
        {
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
        
        logger.debug("preHandle() end");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

}
