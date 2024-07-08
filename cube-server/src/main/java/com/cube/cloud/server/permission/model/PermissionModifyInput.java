package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -2821971952054554644L;


    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    @NotEmpty(message = "权限名称不能为空")
    @Length(max = 50, message = "权限名称长度不能超过" + 50 + "个字。")
    private String name;

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
