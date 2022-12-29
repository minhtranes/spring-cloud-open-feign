package vn.ifa.study.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Retryer;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudOpenFeignClient {

    public static void main(final String[] args) {

        SpringApplication.run(SpringCloudOpenFeignClient.class, args);
    }

    @Bean
    Retryer retryer() {

        return new Retryer.Default();
    }
}
