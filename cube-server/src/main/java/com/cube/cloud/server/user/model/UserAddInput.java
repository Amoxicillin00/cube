package com.cube.cloud.server.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 用户添加输入
 * @author Long
 * @date 2023-01-04 10:54
 */
@Data
public class UserAddInput implements Serializable {

    private static final long serialVersionUID = 3572308907138975650L;

    @ApiModelProperty("所属组织id")
    @NotNull(message = "所属组织不能为空")
    private Long organizationId;

    @ApiModelProperty("名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 20, message = "名称长度不能超过" + 20 + "个字。")
    private String name;

    @ApiModelProperty("登录账号")
    @NotEmpty(message = "登录账号不能为空")
    @Length(max = 50, message = "登录账号长度不能超过" + 50 + "个字。")
    private String account;

    @ApiModelProperty("登录密码")
    @NotEmpty(message = "登录密码不能为空")
    @Length(max = 200, message = "登录密码长度不能超过" + 200 + "个字。")
    private String password;

    @ApiModelProperty("电话号码")
    @NotEmpty(message = "电话号码不能为空")
    private String phoneNumber;

    @ApiModelProperty("电子邮箱")
    private String emailAddress;

    @ApiModelProperty("性别(0 : 女、1 : 男、2 : 未知)")
    private Integer sex;

    @ApiModelProperty("出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty("头像路径Path")
    private String avatarPath;

    @ApiModelProperty("用户类型(0 : 超级管理员、1 : 系统管理员、2 : 普通用户)")
    @NotNull(message = "用户类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度不能超过" + 200 + "个字。")
    private String remark;

    @ApiModelProperty("用户角色id列表")
    private List<Long> roles;
}
