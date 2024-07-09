package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionItemOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = -6756777842315092045L;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;

    /**
     * 唯一路由标识
     */
    @ApiModelProperty("唯一路由标识")
    private String route;

    /**
     * 权限路径URL
     */
    @ApiModelProperty("权限路径URL")
    private String url;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 图标icon
     */
    @ApiModelProperty("图标icon")
    private String icon;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String modifiedName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;
}
