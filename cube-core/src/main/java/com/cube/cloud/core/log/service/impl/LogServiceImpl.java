package com.cube.cloud.core.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.log.entity.OperationLog;
import com.cube.cloud.core.log.mapper.LogMapper;
import com.cube.cloud.core.log.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, OperationLog> implements LogService {
}
