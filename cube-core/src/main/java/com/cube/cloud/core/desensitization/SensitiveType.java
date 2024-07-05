package com.cube.cloud.core.desensitization;

import com.cube.cloud.core.desensitization.impl.*;

/**
 * Created with IntelliJ IDEA.
 * 脱敏类型
 * @author Long
 * @date 2023-08-22 15:35
 */
public enum SensitiveType {

    /**
     * 不处理
     */
    NO(NoDesensitized.INSTANCE),

    /**
     * 默认类型(String类型)
     */
    DEFAULT(DefaultDesensitized.INSTANCE),

    /**
     * 中文姓名
     */
    CHINESE_NAME(ChineseNameDesensitized.INSTANCE),

    /**
     * 身份证号
     */
    ID_CARD(IdCardDesensitized.INSTANCE),

    /**
     * 手机号
     */
    MOBILE_PHONE(MobilePhoneDesensitized.INSTANCE),

    /**
     * 电子邮箱
     */
    EMAIL_ADDRESS(EmailAddressDesensitized.INSTANCE),

    /**
     * 银行卡号
     */
    BANK_CARD(BankCardDesensitized.INSTANCE),
    ;

    /**
     * 脱敏实例
     */
    private final Desensitized instance;

    SensitiveType(Desensitized instance) {
        this.instance = instance;
    }

    public Desensitized getInstance() {
        return instance;
    }
}
