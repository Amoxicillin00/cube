package com.cube.cloud.server.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cube.cloud.server.file.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * 文件Mapper
 * @author Long
 * @date 2023-01-10 15:57
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
}
