package com.cube.cloud.server.document.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentItemOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = -5232505026827877433L;

    @ApiModelProperty(value = "文案标题")
    private String title;

    @ApiModelProperty(value = "文案内容")
    private String content;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改人名称")
    private String modifiedName;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;
}
