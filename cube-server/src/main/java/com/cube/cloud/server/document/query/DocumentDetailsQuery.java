package com.cube.cloud.server.document.query;

import com.cube.cloud.server.document.entity.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-04-01 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentDetailsQuery extends Document {

    private static final long serialVersionUID = -4969311902163364110L;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "修改人名称")
    private String modifiedName;
}
