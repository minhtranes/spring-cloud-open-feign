package vn.ifa.study.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@FeignClient(name = "weathers", url = "http://localhost:8085", path = "api")
public interface WeatherFeign {

    @GetMapping("current")
    JsonNode current(@RequestParam(name = "traceId") String traceId, @RequestParam(name = "timeout") String timeout);
}
