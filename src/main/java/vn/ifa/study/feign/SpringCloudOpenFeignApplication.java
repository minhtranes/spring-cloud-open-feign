package vn.ifa.study.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import vn.ifa.study.feign.weather.WeatherService;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SpringCloudOpenFeignApplication {

    @Autowired
    private WeatherService weatherService;

    public static void main(final String[] args) {

        SpringApplication.run(SpringCloudOpenFeignApplication.class, args);
    }

    @Scheduled(fixedDelay = 5000)
    public void collect() {

        weatherService.currentWeather();
    }
}
