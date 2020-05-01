package com.jmy;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients // 启用feign
@EnableSwagger2 // 启用Swagger
@EnableHystrix // 启用熔断器
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }


    //可以实时显示服务的信息
    @Bean
    public HystrixMetricsStreamServlet createHMSS(){
        return new HystrixMetricsStreamServlet();
    }
    //注册Servlet到SpringBoot项目
    @Bean
    public ServletRegistrationBean registS(HystrixMetricsStreamServlet streamServlet){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean();
        registrationBean.setServlet(streamServlet);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setEnabled(true);
        return registrationBean;
    }


}



