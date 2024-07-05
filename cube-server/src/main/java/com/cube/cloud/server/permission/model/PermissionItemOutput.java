package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
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
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 路由标识
     */
    @ApiModelProperty("路由标识")
    private String route;

    /**
     * 资源类型(0 : 菜单、1 : 按钮、2 : 权限)
     */
    @ApiModelProperty("资源类型(0 : 菜单、1 : 按钮、2 : 权限)")
    private Integer type;

    /**
     * 权限路径
     */
    @ApiModelProperty("权限路径")
    private String path;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer sort;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    private Long parentId;

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
