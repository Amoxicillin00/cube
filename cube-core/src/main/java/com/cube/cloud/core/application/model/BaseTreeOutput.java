package com.cube.cloud.core.application.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 17:12
 */
@Data
public class BaseTreeOutput<T extends Serializable, M> implements Serializable {

    private static final long serialVersionUID = -5845952543654039954L;

    /**
     * id
     */
    @JSONField(ordinal = 1)
    @ApiModelProperty("id")
    private T id;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    private T parentId;

    /**
     * 子节点列表
     */
    @JSONField(ordinal = 99)
    @ApiModelProperty(value = "子节点列表")
    private List<M> childList;

}
