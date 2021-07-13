package com.deltaqin.sys.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebDataConvertConfig extends WebMvcConfigurerAdapter {
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            ObjectMapper objectMapper = new ObjectMapper();
            /**
             * 序列换成json时,将所有的long变成string
             * 因为js中得数字类型不能包含所有的java long值
             */
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            objectMapper.registerModule(simpleModule);
            jackson2HttpMessageConverter.setObjectMapper(objectMapper);
            converters.add(jackson2HttpMessageConverter);
        }
}
