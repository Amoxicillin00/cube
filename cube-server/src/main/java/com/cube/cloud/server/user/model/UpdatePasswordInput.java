package com.cube.cloud.server.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 更新密码
 * @author Long
 * @date 2023-06-06 10:58
 */
@Data
public class UpdatePasswordInput implements Serializable {

    private static final long serialVersionUID = -5888973659431794944L;

    @ApiModelProperty("旧密码")
    @NotEmpty(message = "旧密码不能为空")
    @Length(max = 200, message = "旧密码长度不能超过" + 200 + "个字。")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotEmpty(message = "新密码不能为空")
    @Length(max = 200, message = "新密码长度不能超过" + 200 + "个字。")
    private String newPassword;
}
