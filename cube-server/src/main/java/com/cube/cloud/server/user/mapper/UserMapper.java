package com.cube.cloud.server.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.server.user.entity.User;
import com.cube.cloud.server.user.model.UserPageInput;
import com.cube.cloud.server.user.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 10:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户详细信息
     * @param userId 用户id
     * @return 用户详细信息
     */
    UserQuery getDetails(@Param("userId") Long userId);

    /**
     * 获取分页查询结果
     * @param page mybatis-plus分页对象
     * @param input 查询条件
     * @return 分页查询结果集
     */
    Page<UserQuery> getPage(@Param("page") Page<?> page,
                            @Param("input") UserPageInput input,
                            @Param("organizationId") Long organizationId);
}
