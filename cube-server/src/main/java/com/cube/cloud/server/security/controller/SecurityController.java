package com.cube.cloud.server.security.controller;

import com.cube.cloud.server.security.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-01 15:18
 */
@RestController
@RequestMapping("/security")
@Api(tags = "安全服务")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * 获取RSA公钥
     * @return String
     */
    @ApiOperation(value = "获取RSA公钥")
    @PostMapping("/rsa/public")
    public String getPublicKey() {
        return this.securityService.getPublicKey();
    }

    @ApiOperation(value = "RSA公钥加密")
    @PostMapping("/rsa/public/encrypt")
    public String encryptPublicKey(String data) {
        return this.securityService.encryptByPublicKey(data);
    }

    /*@ApiOperation(value = "RSA私钥解密")
    @PostMapping("/rsa/private/decrypt")
    public String decryptPrivateKey(String data) {
        return this.securityService.decryptByPrivateKey(data);
    }*/
}
