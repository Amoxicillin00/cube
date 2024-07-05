package com.cube.cloud.server.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.enums.StatusEnum;
import com.cube.cloud.server.organization.entity.Organization;
import com.cube.cloud.server.organization.mapper.OrganizationMapper;
import com.cube.cloud.server.organization.model.*;
import com.cube.cloud.server.organization.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:23
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);



    /**
     * 添加
     * @param input 输入参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(OrganizationAddInput input) {
        logger.info("Organization 这是一个添加");
    }

    /**
     * 删除
     * @param input 主键id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        logger.info("Organization 这是一个删除");
    }

    /**
     * 修改
     * @param input 修改参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(OrganizationModifyInput input) {
        logger.info("Organization 这是一个修改");
    }

    /**
     * 详情
     * @param input 主键id
     * @return OrganizationDetailsOutput
     */
    @Override
    public OrganizationDetailsOutput getDetails(BaseIdInput input) {
        logger.info("Organization 这是一个详情");
        return new OrganizationDetailsOutput();
    }

    /**
     * 列表查询
     * @return List<OrganizationItemOutput>
     */
    @Override
    public List<OrganizationItemOutput> getList() {
        logger.info("Organization 这是一个列表查询");
        return new ArrayList<>();
    }

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<OrganizationItemOutput>
     */
    @Override
    public BasePageOutput<OrganizationItemOutput> getPage(OrganizationPageInput input) {
        logger.info("Organization 这是一个分页查询");
        return new BasePageOutput<>();
    }

    @Override
    public Organization getOrganizationById(Long organizationId) {
        LambdaQueryWrapper<Organization> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Organization::getId, organizationId)
                .eq(Organization::getStatus, StatusEnum.ENABLE.getCode());
        return this.getOne(wrapper);
    }
}
