package com.cube.cloud.server.login.model;

import com.cube.cloud.core.log.annotation.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 登录输入实体
 * @author Long
 * @date 2023-05-18 16:10
 */
@Data
public class LoginInput implements Serializable {

    private static final long serialVersionUID = -6622905773615984912L;

    @Account
    @ApiModelProperty("账号")
    @NotEmpty(message = "账号不能为空")
    private String account;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
