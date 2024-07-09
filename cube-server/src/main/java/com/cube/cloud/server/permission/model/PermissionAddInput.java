package com.cube.cloud.server.permission.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 添加参数
 * @author Long
 * @date 2023-01-04 11:10
 */
@Data
public class PermissionAddInput implements Serializable {

    private static final long serialVersionUID = 6821489241838467322L;

    @ApiModelProperty("资源模块id")
    @NotNull(message = "资源模块不能为空")
    private Long moduleId;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    @NotEmpty(message = "权限名称不能为空")
    @Length(max = 50, message = "权限名称长度不能超过" + 50 + "个字。")
    private String name;

    /**
     * 唯一路由标识
     */
    @ApiModelProperty("唯一路由标识")
    @NotEmpty(message = "唯一路由标识不能为空")
    @Length(max = 50, message = "唯一路由标识长度不能超过" + 50 + "个字。")
    private String route;

    /**
     * 权限路径URL
     */
    @ApiModelProperty("权限路径URL")
    private String url;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer sort;

    /**
     * 图标icon
     */
    @ApiModelProperty("图标icon")
    private String icon;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
