package vn.ifa.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudOpenFeignClient {

    public static void main(final String[] args) {

        SpringApplication.run(SpringCloudOpenFeignClient.class, args);
    }
    
}
