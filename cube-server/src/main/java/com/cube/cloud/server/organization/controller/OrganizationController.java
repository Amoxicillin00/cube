package com.cube.cloud.server.organization.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.organization.model.*;
import com.cube.cloud.server.organization.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 组织服务
 * @author Long
 * @date 2023-01-04 11:21
 */
@RestController
@RequestMapping("/sys/organization")
@Api(tags = "组织服务")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 添加组织
     * @param input 输入参数
     */
    @ApiOperation("添加组织")
    @PostMapping("/add")
    @Log(name = "添加组织", type = OperateType.CREATE)
    public void add(@Valid @RequestBody OrganizationAddInput input) {
        this.organizationService.add(input);
    }

    /**
     * 删除组织
     * @param input 输入参数
     */
    @ApiOperation("删除组织")
    @PostMapping("/delete")
    @Log(name = "删除组织", type = OperateType.DELETE)
    public void delete(@Valid @RequestBody BaseIdInput input) {
        this.organizationService.delete(input);
    }

    /**
     * 修改组织信息
     * @param input 输入参数
     */
    @ApiOperation("修改组织信息")
    @PostMapping("/update")
    @Log(name = "修改组织信息", type = OperateType.UPDATE)
    public void update(@Valid @RequestBody OrganizationModifyInput input) {
        this.organizationService.update(input);
    }

    /**
     * 查询组织详情
     * @param input 输入参数
     * @return 组织详情
     */
    @ApiOperation("查询组织详情")
    @PostMapping("/details")
    @Log(name = "查询组织详情", type = OperateType.SELECT)
    public OrganizationDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return this.organizationService.getDetails(input);
    }

    /**
     * 查询列表列表
     * @return 组织信息列表
     */
    @ApiOperation("查询组织列表")
    @PostMapping("/list")
    @Log(name = "查询列表列表", type = OperateType.SELECT)
    public List<OrganizationItemOutput> list() {
        return this.organizationService.getList();
    }

    /**
     * 查询组织列表-分页查询
     * @param input 输入参数
     * @return 组织信息列表
     */
    @ApiOperation("查询组织列表-分页查询")
    @PostMapping("/page")
    @Log(name = "查询组织列表-分页查询", type = OperateType.SELECT)
    public BasePageOutput<OrganizationItemOutput> page(@Valid @RequestBody OrganizationPageInput input) {
        return this.organizationService.getPage(input);
    }
}
