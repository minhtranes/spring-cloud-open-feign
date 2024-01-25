package vn.ifa.study.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import vn.ifa.study.feign.os.FileService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackageClasses = {FileService.class})
public class ObjectStorageClientApplication {


    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(ObjectStorageClientApplication.class, args);
    }

    @PostConstruct
    void init() throws IOException {
        Path dir = Path.of("C:\\Users\\minh.tran\\Downloads");
        String objectKey = "expense/test_data.csv";
        final File file = Optional.of(dir)
                                  .map(d -> d.resolve("expense_abc.png"))
                                  .map(Path::toFile)
                                  .get();
        final byte[] bytes = FileUtils.readFileToByteArray(file);
        fileService.putObject(objectKey, bytes);
        final JsonNode res = fileService.getObject(objectKey);
        final byte[] objectBytes = Optional.of(res)
                                           .map(jsn -> jsn.get("binary"))
                                           .map(JsonNode::asText)
                                           .map(Base64.getDecoder()::decode)
                                           .get();
        final Path targetFile = dir.resolve("expense_abc_download.png");
        targetFile.toFile()
                  .createNewFile();
        Files.write(targetFile, objectBytes);
    }
}
