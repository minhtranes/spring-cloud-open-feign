package vn.ifa.study.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import vn.ifa.study.config.WeathersAuthConfig;

@FeignClient(name = "weathers", url = "http://localhost:8085", path = "api", configuration = WeathersAuthConfig.class)
public interface WeatherFeign {

    @GetMapping("/current")
    Response current(@RequestParam(name = "traceId") String traceId, @RequestParam(name = "timeout") String timeout, @RequestParam(name = "locations") String[] locations) ;
    
    @PostMapping("/tomorrow")
    Response tomorrow(@RequestParam(name = "traceId") String traceId, @RequestParam(name = "timeout") String timeout, @RequestBody Country country);
}
