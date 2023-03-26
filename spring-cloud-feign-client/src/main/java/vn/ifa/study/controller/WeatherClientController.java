package vn.ifa.study.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.ifa.study.feign.Country;
import vn.ifa.study.feign.Response;
import vn.ifa.study.feign.WeatherFeign;

@RestController
@RequestMapping("api")
public class WeatherClientController {
    
    @Autowired
    private WeatherFeign weatherClient;

    @GetMapping("current")
    public Response current(
        @RequestParam(name = "timeout", required = false, defaultValue = "PT0S") final String timeout,
        @RequestParam(name = "locations", required = false) List<String> locations) {

        return weatherClient.current(UUID.randomUUID().toString(), timeout, locations.toArray(new String[locations.size()]));
    }
    
    @GetMapping("tomorrow")
    public Response tomorrow(
        @RequestParam(name = "timeout", required = false, defaultValue = "PT0S") final String timeout) {

        return weatherClient.tomorrow(UUID.randomUUID().toString(), timeout, new Country("VN"));
    }
}
