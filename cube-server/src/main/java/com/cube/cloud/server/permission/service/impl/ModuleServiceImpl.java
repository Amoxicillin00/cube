package com.cube.cloud.server.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.server.permission.entity.Module;
import com.cube.cloud.server.permission.mapper.ModuleMapper;
import com.cube.cloud.server.permission.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2024-06-07 14:58
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {

    private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);
}
