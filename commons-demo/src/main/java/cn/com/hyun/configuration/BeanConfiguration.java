package cn.com.hyun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/5/31.
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(stringHttpMessageConverter);
        template.setMessageConverters(list);
        return template;
    }
}
