package com.cube.cloud.server.document.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 15:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -4308931366813019468L;

    @ApiModelProperty(value = "文案标题")
    @Length(max = 20, message = "文案标题长度不能超过" + 20 + "个字。")
    private String title;

    @ApiModelProperty(value = "文案内容")
    @Length(max = 200, message = "文案内容长度不能超过" + 200 + "个字。")
    private String content;

    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度不能超过" + 200 + "个字。")
    private String remark;
}
