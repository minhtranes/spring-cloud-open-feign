package vn.ifa.study.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SpringCloudOpenFeignApplication {

    public static void main(final String[] args) {

        SpringApplication.run(SpringCloudOpenFeignApplication.class, args);
    }

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedDelay = 5000)
    public void collect() {

        weatherService.currentWeather();
    }
}
