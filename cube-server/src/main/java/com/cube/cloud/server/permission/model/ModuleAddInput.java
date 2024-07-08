package com.cube.cloud.server.permission.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2024-06-07 16:34
 */
@Data
public class ModuleAddInput implements Serializable {

    private static final long serialVersionUID = -1286694259468933200L;


    /**
     * 模块名称
     */
    @ApiModelProperty("模块名称")
    @NotEmpty(message = "模块名称不能为空")
    @Length(max = 50, message = "模块名称长度不能超过" + 50 + "个字。")
    private String name;

    /**
     * 唯一路由名称
     */
    @ApiModelProperty("唯一路由标识")
    @NotEmpty(message = "唯一路由标识不能为空")
    @Length(max = 50, message = "唯一路由标识长度不能超过" + 50 + "个字。")
    private String route;

    /**
     * 是否需要授权(false : 不需要、true : 需要)
     */
    @ApiModelProperty("是否需要授权(false : 不需要、true : 需要)")
    private boolean isAuthorize;

    /**
     * 模块类型(0 : Web、1 : APP、2 : H5)
     */
    @ApiModelProperty("模块类型(0 : Web、1 : APP、2 : H5)")
    @NotNull(message = "模块类型不能为空")
    private Integer type;

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
     * 资源模块状态(0 : 禁用、1 : 启用)
     */
    @TableField(value = "status")
    private Integer status;
}
