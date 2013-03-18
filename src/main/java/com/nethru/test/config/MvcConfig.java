package com.nethru.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(
    basePackages="com.nethru.test",
    excludeFilters=@ComponentScan.Filter(Configuration.class)
)
public class MvcConfig
{
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
    
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(VIEW_RESOLVER_PREFIX);
        resolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return resolver;
    }
    
}
