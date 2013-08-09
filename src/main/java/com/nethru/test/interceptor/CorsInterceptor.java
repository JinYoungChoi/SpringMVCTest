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
 * Origin 헤더가 request 에 존재할 경우, CORS 가능하도록 응답 헤더를 추가한다.
 * 
 * @author mj
 *
 */
public class CorsInterceptor implements HandlerInterceptor
{
    private static final Logger logger = LoggerFactory.getLogger(CorsInterceptor.class);
    
    private static final String  ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
    private static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String  REQUEST_HEADER_ORIGIN            = "Origin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        logger.debug("preHandle() start");
        
        String origin = request.getHeader(REQUEST_HEADER_ORIGIN);
        logger.debug("Origin Header: {}", origin);

        // CORS 가능하도록 응답 헤더 추가
        if (StringUtils.hasLength(origin))
        {
            // 요청한 도메인에 대해 CORS 를 허용한다. 제한이 필요하다면 필요한 값으로 설정한다.
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            
            // credentials 허용
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
        
        logger.debug("preHandle() end");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        // do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // do nothing
    }

}
