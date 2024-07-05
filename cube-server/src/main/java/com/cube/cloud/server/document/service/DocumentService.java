package com.cube.cloud.server.document.service;

import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.server.document.model.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:28
 */
public interface DocumentService {

    /**
     * 添加
     * @param input 输入参数
     */
    void add(DocumentAddInput input);

    /**
     * 删除
     * @param input 主键id
     */
    void delete(BaseIdInput input);

    /**
     * 修改
     * @param input 修改参数
     */
    void update(DocumentModifyInput input);

    /**
     * 详情
     * @param input 主键id
     * @return DocumentDetailsOutput
     */
    DocumentDetailsOutput getDetails(BaseIdInput input);

    /**
     * 列表查询
     * @return List<DocumentItemOutput>
     */
    List<DocumentItemOutput> getList();

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<DocumentItemOutput>
     */
    BasePageOutput<DocumentItemOutput> getPage(DocumentPageInput input);
}
