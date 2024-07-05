package com.cube.cloud.server.log.controller;

import com.cube.cloud.server.log.service.OperationLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-08-24 15:55
 */
@RestController
@RequestMapping("/sys/operation/log")
@Api(tags = "日志服务")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;
}
