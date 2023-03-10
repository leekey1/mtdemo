package cn.com.hyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhg on 2015/1/5
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrix
//@EnableHystrixDashboard
@EnableSwagger2
public class ApplicationServerDemo {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationServerDemo.class, args);
    }
}
