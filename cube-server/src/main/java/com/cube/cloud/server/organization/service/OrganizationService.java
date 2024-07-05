package com.cube.cloud.server.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.server.organization.entity.Organization;
import com.cube.cloud.server.organization.model.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:22
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 添加
     * @param input 输入参数
     */
    void add(OrganizationAddInput input);

    /**
     * 删除
     * @param input 主键id
     */
    void delete(BaseIdInput input);

    /**
     * 修改
     * @param input 修改参数
     */
    void update(OrganizationModifyInput input);

    /**
     * 详情
     * @param input 主键id
     * @return OrganizationDetailsOutput
     */
    OrganizationDetailsOutput getDetails(BaseIdInput input);

    /**
     * 列表查询
     * @return List<OrganizationItemOutput>
     */
    List<OrganizationItemOutput> getList();

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<OrganizationItemOutput>
     */
    BasePageOutput<OrganizationItemOutput> getPage(OrganizationPageInput input);

    /**
     * 获取组织信息
     * @param organizationId 组织id
     * @return 组织信息
     */
    Organization getOrganizationById(Long organizationId);
}
