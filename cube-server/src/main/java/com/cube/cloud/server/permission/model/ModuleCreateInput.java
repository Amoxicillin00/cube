package com.cube.cloud.server.permission.model;

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
public class ModuleCreateInput implements Serializable {

    private static final long serialVersionUID = -1286694259468933200L;


    @ApiModelProperty("模块名称")
    @NotEmpty(message = "模块名称不能为空")
    @Length(max = 50, message = "模块名称长度不能超过" + 50 + "个字。")
    private String name;

    @ApiModelProperty("路由标识")
    @NotEmpty(message = "路由标识不能为空")
    @Length(max = 50, message = "路由标识长度不能超过" + 50 + "个字。")
    private String route;

    @ApiModelProperty("是否需要授权(false : 不需要、true : 需要)")
    private boolean isAuthorize;

    @ApiModelProperty("模块类型(0 : Web、1 : APP、2 : H5)")
    @NotNull(message = "模块类型不能为空")
    private Integer type;

    @ApiModelProperty("顺序")
    private Integer sort;

}
