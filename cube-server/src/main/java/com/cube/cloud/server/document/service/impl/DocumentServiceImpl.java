package com.cube.cloud.server.document.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.server.document.entity.Document;
import com.cube.cloud.server.document.mapper.DocumentMapper;
import com.cube.cloud.server.document.model.*;
import com.cube.cloud.server.document.query.DocumentDetailsQuery;
import com.cube.cloud.server.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:28
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);


    /**
     * 添加
     * @param input 输入参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(DocumentAddInput input) {
        Document document = MapUtils.map(input, Document.class);
        ExceptionUtils.check(Objects.nonNull(this.getByTitle(document.getTitle())), "文案标题已存在");
        // 保存
        this.save(document);
    }

    /**
     * 删除
     * @param input 主键id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        this.removeById(input.getId());
    }

    /**
     * 修改
     * @param input 修改参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DocumentModifyInput input) {
        Document document = this.getById(input.getId());
        if (Objects.isNull(document)) {
            logger.info("文案不存在");
            return;
        }
        BeanUtils.copyProperties(input, document);
        this.updateById(document);
    }

    /**
     * 详情
     * @param input 主键id
     * @return DocumentDetailsOutput
     */
    @Override
    public DocumentDetailsOutput getDetails(BaseIdInput input) {
        DocumentDetailsQuery detailsQuery = this.baseMapper.getDetails(input.getId());
        return MapUtils.map(detailsQuery, DocumentDetailsOutput.class);
    }

    /**
     * 列表查询
     * @return List<DocumentItemOutput>
     */
    @Override
    public List<DocumentItemOutput> getList() {
        LambdaQueryWrapper<Document> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(Document::getCreatedTime);
        return MapUtils.mapForList(this.list(wrapper), DocumentItemOutput.class);
    }

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<DocumentItemOutput>
     */
    @Override
    public BasePageOutput<DocumentItemOutput> getPage(DocumentPageInput input) {
        Page<DocumentDetailsQuery> page =this.baseMapper.getPage(Page.of(input.getCurrent(), input.getSize()), input);
        BasePageOutput<DocumentItemOutput> basePageOutput = BasePageOutput.build(page.getCurrent(), page.getSize(), page.getTotal());
        basePageOutput.setRecords(MapUtils.mapForList(page.getRecords(), DocumentItemOutput.class));
        return basePageOutput;
    }


    /**
     * 根据标题查询文案信息
     * @param title 文案标题
     * @return Document
     */
    public Document getByTitle(String title) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(Document::getTitle, title)
                .one();
    }
}
