package com.cube.cloud.server.user.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * 用户信息输出
 * @author Long
 * @date 2023-01-04 10:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserItemOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = 1482931395908468418L;

    @ApiModelProperty("所属组织id")
    private Long organizationId;

    @ApiModelProperty("组织名称")
    private String organizationName;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("登录账号")
    private String account;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("账号状态(0 : 禁用、1 : 启用)")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty("修改人")
    private String modifiedName;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    @ApiModelProperty("角色名称")
    private String roleNameList;
}
