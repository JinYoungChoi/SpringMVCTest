package com.nethru.test;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Preflight 요청을 처리하기 위한 컨트롤러.
 * CORS 가 필요한 다른 컨트롤러가 상속해서 사용한다.
 * @author mj
 *
 */
public class CorsController
{
    private static final Logger logger = LoggerFactory.getLogger(CorsController.class);
    
    private static final String  ACCESS_CONTROL_REQUEST_METHOD    = "Access-Control-Request-Method";
    private static final String  ACCESS_CONTROL_REQUEST_HEADERS   = "Access-Control-Request-Headers";
    private static final String  ACCESS_CONTROL_ALLOW_METHODS     = "Access-Control-Allow-Methods";
    private static final String  ACCESS_CONTROL_ALLOW_HEADERS     = "Access-Control-Allow-Headers";
    private static final String  ACCESS_CONTROL_MAX_AGE           = "Access-Control-Max-Age";
    private static final Integer DAY_IN_SECONDS                   = 24 * 60 * 60;
    
    /**
     * Prefilght 요청 처리.
     * OPTIONS 메소드로 요청이 들어온 경우, 이를 처리해준다.
     * @param requestMethods
     * @param requestHeaders
     * @param response
     */
    @RequestMapping(method=RequestMethod.OPTIONS)
    public void handleOptionsRequest(@RequestHeader(value=ACCESS_CONTROL_REQUEST_METHOD,  required=false) String requestMethods,
                                     @RequestHeader(value=ACCESS_CONTROL_REQUEST_HEADERS, required=false) String requestHeaders,
                                     HttpServletResponse response)
    {
        logger.debug("handleOptionsRequest() start");
        
        logger.debug("Access-Control-Request-Method Header : {}", requestMethods);
        logger.debug("Access-Control-Request-Headers Header : {}", requestHeaders);
        
        if (StringUtils.hasLength(requestMethods))
        {
            response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, requestMethods);
        }
        
        if (StringUtils.hasLength(requestHeaders))
        {
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);
        }
        
        response.setHeader(ACCESS_CONTROL_MAX_AGE, DAY_IN_SECONDS.toString());
        
        logger.debug("handleOptionsRequest() end");
    }
}
