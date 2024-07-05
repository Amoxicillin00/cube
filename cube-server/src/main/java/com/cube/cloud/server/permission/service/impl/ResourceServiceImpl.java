package com.cube.cloud.server.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.server.permission.entity.Resource;
import com.cube.cloud.server.permission.mapper.ResourceMapper;
import com.cube.cloud.server.permission.service.ResourceService;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
}
