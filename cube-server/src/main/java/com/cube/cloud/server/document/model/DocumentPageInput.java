package com.cube.cloud.server.document.model;

import com.cube.cloud.core.application.model.BasePageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 15:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentPageInput extends BasePageInput {

    private static final long serialVersionUID = 8396259730773079152L;

    @ApiModelProperty(value = "文案标题")
    private String title;
}
