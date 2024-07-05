package com.cube.cloud.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BaseStatusInput;
import com.cube.cloud.server.user.entity.User;
import com.cube.cloud.server.user.model.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 10:54
 */
public interface UserService extends IService<User> {

    /**
     * 添加
     * @param input 新用户参数
     */
    void add(UserAddInput input);

    /**
     * 删除
     * @param input 主键id
     */
    void delete(BaseIdInput input);

    /**
     * 修改
     * @param input 修改参数
     */
    void update(UserModifyInput input);

    /**
     * 详情
     * @param input 主键id
     * @return UserDetailsOutput
     */
    UserDetailsOutput getDetails(BaseIdInput input);

    /**
     * 列表查询
     * @return List<UserItemOutput>
     */
    List<UserItemOutput> getList();

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<UserItemOutput>
     */
    BasePageOutput<UserItemOutput> getPage(UserPageInput input);

    /**
     * 更新密码
     * @param input 新旧密码（RSA）
     */
    void updatePassword(UpdatePasswordInput input);

    /**
     * 修改用户账号状态(0 : 禁用、1 : 启用)
     * @param input 修改状态信息
     */
    void updateStatus(BaseStatusInput input);

    /**
     * 根据账号查询用户信息
     * @param account 账号
     * @return 用户信息
     */
    User getUserByAccount(String account);

    /**
     * 校验电话号码唯一性(userId不为null时，则需要排除这个userId的数据)
     * @param phoneNumber 电话号码
     * @param organizationId 组织id
     * @param userId 排除的用户id
     * @return 电话号码是否唯一
     */
    Boolean checkPhoneNumber(String phoneNumber, Long organizationId, Long userId);

    /**
     * 获取角色用户列表
     * @param roleId 角色id
     * @return 角色用户列表
     */
    List<RoleUserOutput> getListByRoleId(Long roleId);
}
