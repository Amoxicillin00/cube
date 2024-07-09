package com.cube.cloud.server.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.server.permission.entity.Module;
import com.cube.cloud.server.permission.model.ModulePageInput;
import com.cube.cloud.server.permission.query.ModuleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2024-06-07 14:45
 */
@Mapper
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * 分页查询
     * @param page 分页对象
     * @param input 输入参数
     * @return 资源模块结果集
     */
    Page<ModuleQuery> selectModuleByPage(@Param("page") Page<?> page, @Param("input") ModulePageInput input);
}
