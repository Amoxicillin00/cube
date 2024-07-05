package com.cube.cloud.server.document.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:30
 */
@Data
public class DocumentAddInput implements Serializable {

    private static final long serialVersionUID = -5016632307268469655L;

    @ApiModelProperty(value = "文案标题")
    @NotEmpty(message = "文案标题不能为空")
    @Length(max = 20, message = "文案标题长度不能超过" + 20 + "个字。")
    private String title;

    @ApiModelProperty(value = "文案内容")
    @Length(max = 200, message = "文案内容长度不能超过" + 200 + "个字。")
    private String content;

    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度不能超过" + 200 + "个字。")
    private String remark;


}
