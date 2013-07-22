package com.nethru.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nethru.test.interceptor.CorsInterceptor;

/**
 * MVC 설정용 클래스.
 * 이 클래스는 스프링의 sevlet-context.xml 의 역할을 대신한다.
 * @author mj
 *
 */
@Configuration
@EnableWebMvc
@EnableAsync // @Async 어노테이션을 사용하기 위함
@ComponentScan(
    basePackages="com.nethru.test",
    excludeFilters=@ComponentScan.Filter(Configuration.class)
)
public class MvcConfig extends WebMvcConfigurerAdapter // 인터셉터를 추가하기 위해 WebMvcConfigurerAdapter 를 상속한다
{
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    /**
     * 인터셉터 추가
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new CorsInterceptor());
    }
}
