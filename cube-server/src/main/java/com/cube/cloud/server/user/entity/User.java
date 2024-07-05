package com.cube.cloud.server.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = -3574305673958149796L;

    /**
     * 所属组织id
     */
    @TableField(value = "organization_id")
    private Long organizationId;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 登录密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    @TableField(value = "email_address")
    private String emailAddress;

    /**
     * 性别(0 : 女、1 : 男)
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /**
     * 头像路径Path
     */
    @TableField(value = "avatar_path")
    private String avatarPath;

    /**
     * 用户类型(0 : 超级管理员、1 : 系统管理员、2 : 普通用户)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 账号状态(0 : 禁用、1 : 启用)
     */
    @TableField(value = "status")
    private Integer status;
}
