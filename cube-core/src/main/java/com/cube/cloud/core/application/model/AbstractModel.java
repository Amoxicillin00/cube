package com.cube.cloud.core.application.model;

import com.cube.cloud.core.log.annotation.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 实体抽象
 * @author Long
 * @date 2023-01-04 11:46
 */
@Data
public abstract class AbstractModel<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 6612543568326354857L;

    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private T id;
}
