package com.cube.cloud.server.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *用户详情输出
 * @author Long
 * @date 2023-01-04 16:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetailsOutput extends UserItemOutput{

    private static final long serialVersionUID = 4038645437900390280L;

    @ApiModelProperty("用户类型(0 : 超级管理员、1 : 系统管理员、2 : 普通用户)")
    private Integer type;

    @ApiModelProperty("电子邮箱")
    private String emailAddress;

    @ApiModelProperty("性别(0 : 女、1 : 男、2 : 未知)")
    private Integer sex;

    @ApiModelProperty("出生日期")
    private Data birthday;

    @ApiModelProperty("头像路径Path")
    private String avatarPath;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("用户角色id列表")
    private List<Long> roleIdList;

}
