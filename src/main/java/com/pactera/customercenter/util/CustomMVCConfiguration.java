package com.pactera.customercenter.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {
//    @Bean
////    public HttpMessageConverter<String> responseBodyConverter() {
////        StringHttpMessageConverter converter = new StringHttpMessageConverter(
////                Charset.forName("UTF-8"));
////
////        return converter;
////    }
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        System.out.println("1233444445454535342424242424    dfhakfjklajkdf");

        StringHttpMessageConverter stringConverter= new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(stringConverter);


        FastJsonHttpMessageConverter4 fastJsonConverter = new FastJsonHttpMessageConverter4();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        fastJsonConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        MediaType text_plain = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        MediaType text_html = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
        MediaType x_www_form_urlencoded_utf8 = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        supportedMediaTypes.add(text_html);
        supportedMediaTypes.add(text_plain);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(x_www_form_urlencoded_utf8);

        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(fastJsonConverter);
        super.configureMessageConverters(converters);

    }
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/testName").setViewName("testName.html");
    }
}

