package com.cube.cloud.server;

import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.knife4j.Knife4jScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = Constants.SCAN_BASE_PACKAGE)
@MapperScan(basePackages = {Constants.SCAN_MAPPER, Constants.SCAN_CORE_MAPPER})
@Knife4jScan(title = "Cube API 开发文档", basePackage = Constants.SCAN_SWAGGER_PACKAGE)
public class CubeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubeServerApplication.class, args);
    }

}
