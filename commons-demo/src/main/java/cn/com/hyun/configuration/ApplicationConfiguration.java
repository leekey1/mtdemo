package cn.com.hyun.configuration;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by helloworld on 15/8/12.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"cn.com.hyun"})
@EnableAsync(proxyTargetClass = true)
@EnableSwagger2
@Slf4j
public class ApplicationConfiguration implements WebMvcConfigurer {
    public static final String TRANSACTION_MANAGER = "transactionManager";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if (StringUtils.isBlank(source)) {
                    return null;
                }
                try {
                    return sdf.parse(source);
                } catch (ParseException e) {
                    log.error("[date format error] - [e] = {}", ExceptionUtils.getStackTrace(e));
                    throw new IllegalArgumentException("日期格式错误, 需要yyyy-MM-dd");
                }
            }
        };
    }


    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(100);
        threadPoolTaskExecutor.setMaxPoolSize(500);
        threadPoolTaskExecutor.setKeepAliveSeconds(3600);

        return threadPoolTaskExecutor;
    }

}
