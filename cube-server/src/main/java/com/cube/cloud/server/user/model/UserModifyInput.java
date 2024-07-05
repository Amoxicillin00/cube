package com.cube.cloud.server.user.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 用户修改输入
 * @author Long
 * @date 2023-01-04 16:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -8811798646510139019L;

    @ApiModelProperty("所属组织id")
    @NotNull(message = "所属组织不能为空")
    private Long organizationId;

    @ApiModelProperty("名称")
    @Length(max = 20, message = "名称长度不能超过" + 20 + "个字。")
    private String name;

    @ApiModelProperty("昵称")
    @Length(max = 20, message = "昵称长度不能超过" + 20 + "个字。")
    private String nickName;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("电子邮箱")
    private String emailAddress;

    @ApiModelProperty("性别(0 : 女、1 : 男、2 : 未知)")
    private Integer sex;

    @ApiModelProperty("头像路径Path")
    private String avatarPath;

    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度不能超过" + 200 + "个字。")
    private String remark;

    @ApiModelProperty("用户角色id列表")
    @NotEmpty(message = "角色不能为空")
    private List<Long> roles;
}
