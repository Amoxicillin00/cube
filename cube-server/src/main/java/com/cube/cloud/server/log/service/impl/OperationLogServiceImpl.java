package com.cube.cloud.server.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.log.entity.OperationLog;
import com.cube.cloud.server.log.mapper.OperationLogMapper;
import com.cube.cloud.server.log.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-08-24 15:58
 */
@Slf4j
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
}
