package vn.ifa.study.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private WeatherFeign weatherFeign;

    public JsonNode currentWeather() {

        log.info("Collect current weather");
        final JsonNode resp = weatherFeign.current("PT10S");
        log.info("Done collecting current weather. respn= [{}]", resp != null ? resp.toString() : "NULL_VALUE");
        return resp;
    }
}
