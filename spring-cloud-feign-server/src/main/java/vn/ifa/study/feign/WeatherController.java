package vn.ifa.study.feign;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
public class WeatherController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("current")
    public JsonNode current(
        @RequestParam(name = "traceId", required = true) final String traceId,
        @RequestParam(name = "timeout", required = false, defaultValue = "PT0S") final String timeout,
        @RequestHeader Map<String, String> headers) throws JsonProcessingException {
        
        log.info("Received request with headers: {} ", mapper.writeValueAsString(headers));
        
        ObjectNode res = mapper.createObjectNode();
        Duration to = Duration.parse(timeout);

        log.info("Process request {} with processing time {}", traceId, to);

        res.put("traceId", traceId);

        if (to.isNegative() || to.isZero()) {
            log.info("Quick respond request {}", traceId);
        } else {

            try {
                Thread.sleep(to.toMillis());
                log.info("Process request {} done", traceId);
            }
            catch (InterruptedException e) {
            }

        }

        res.put("message", "Process request successfully");
        return res;
    }
}
