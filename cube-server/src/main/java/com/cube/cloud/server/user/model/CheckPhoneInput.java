package com.cube.cloud.server.user.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * 校验手机号
 * @author Long
 * @date 2023-06-16 14:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckPhoneInput extends AbstractModel<Long> {

    private static final long serialVersionUID = 5146708539727238359L;

    @ApiModelProperty("所属组织id")
    @NotNull(message = "所属组织不能为空")
    private Long organizationId;

    @ApiModelProperty("电话号码")
    @NotEmpty(message = "电话号码不能为空")
    private String phoneNumber;
}
