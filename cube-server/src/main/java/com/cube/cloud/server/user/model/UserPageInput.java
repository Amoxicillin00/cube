package com.cube.cloud.server.user.model;

import com.cube.cloud.core.application.model.BasePageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * 用户筛选输入
 * @author Long
 * @date 2023-01-04 16:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageInput extends BasePageInput {

    private static final long serialVersionUID = -247648589204177331L;

    @ApiModelProperty("名称")
    @Length(max = 20, message = "名称长度不能超过" + 20 + "个字。")
    private String name;

    @ApiModelProperty("昵称")
    @Length(max = 20, message = "名称长度不能超过" + 20 + "个字。")
    private String nickName;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("账号状态(0 : 禁用、1 : 启用)")
    private Integer status;
}
