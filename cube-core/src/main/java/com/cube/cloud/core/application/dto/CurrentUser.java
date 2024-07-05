package com.cube.cloud.core.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * 当前用户信息
 * @author Long
 * @date 2023-06-14 11:35
 */
@Data
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 1409225274868074431L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 账号
     */
    private String account;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    private String emailAddress;

    /**
     * 性别(0 : 女、1 : 男)
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 头像路径
     */
    private String avatarPath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属组织id
     */
    private Long organizationId;

    /**
     * 所属组织名称
     */
    private String organizationName;

    /**
     * 用户角色
     */
    private String roles;

}
