package com.cube.cloud.server.login.model;

import com.cube.cloud.core.application.model.AbstractModel;
import com.cube.cloud.core.desensitization.annotation.FieldSensitive;
import com.cube.cloud.core.desensitization.SensitiveType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * 登录返回实体
 * @author Long
 * @date 2023-05-18 16:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = 5878143510331094118L;

    /**
     * token令牌
     */
    @ApiModelProperty("token令牌")
    private String token;

    /**
     * token令牌头
     */
    @ApiModelProperty("token令牌头")
    private String tokenName;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @FieldSensitive(SensitiveType.CHINESE_NAME)
    private String name;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickName;

    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String loginAccount;


    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    @FieldSensitive(SensitiveType.MOBILE_PHONE)
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    @FieldSensitive(SensitiveType.EMAIL_ADDRESS)
    private String emailAddress;

    /**
     * 性别(0 : 女、1 : 男)
     */
    @ApiModelProperty("性别(0 : 女、1 : 男)")
    private Integer sex;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private LocalDateTime birthday;

    /**
     * 头像路径
     */
    @ApiModelProperty("头像路径")
    private String avatarPath;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 所属组织id
     */
    @ApiModelProperty("所属组织id")
    private Long organizationId;

    /**
     * 所属组织名称
     */
    @ApiModelProperty("所属组织名称")
    private String organizationName;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色")
    private String roles;
}
