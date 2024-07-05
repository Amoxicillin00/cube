package com.cube.cloud.server.role.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:02
 */
@Data
public class RoleItemOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = 5558544070926199229L;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 组织名称
     */
    @ApiModelProperty("组织名称")
    private String organizationName;

    /**
     * 角色状态(0 : 禁用、1 : 启用)
     */
    @ApiModelProperty("角色状态(0 : 禁用、1 : 启用)")
    private Integer status;

    /**
     * 创建人名称
     */
    @ApiModelProperty("创建人名称")
    private String createdName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改人名称
     */
    @ApiModelProperty("修改人名称")
    private String modifiedName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;
}
