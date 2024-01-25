package vn.ifa.study.feign.os;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fileService", url = "${spring.cloud.openfeign.client.config.fileService.url}")
public interface FileService {
    @PostMapping(path = "ps_data", produces = "application/binary")
    JsonNode putObject(@RequestParam(name = "objectKey") String objectKey, @RequestBody byte[] fileConent);

    @GetMapping(path = "ps_data", consumes = "application/binary")
    JsonNode getObject(@RequestParam(name = "objectKey") String objectKey);
}
