package vn.ifa.study.feign;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("api")
public class WeatherClientController {
    @Autowired
    private WeatherFeign weatherClient;

    @GetMapping("current")
    public JsonNode current(
        @RequestParam(name = "timeout", required = false, defaultValue = "PT0S") final String timeout) {

        return weatherClient.current(UUID.randomUUID()
                .toString(), timeout);
    }
}
