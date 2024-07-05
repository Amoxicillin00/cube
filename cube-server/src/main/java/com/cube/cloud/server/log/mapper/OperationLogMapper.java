package com.cube.cloud.server.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cube.cloud.core.log.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-08-24 15:56
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
