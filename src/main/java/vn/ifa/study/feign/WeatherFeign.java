package vn.ifa.study.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.databind.JsonNode;

@FeignClient(name = "weathers")
public interface WeatherFeign {

    @GetMapping("/api/weather")
    JsonNode current(@RequestHeader(name = "read-time-out") String timeout);
}
