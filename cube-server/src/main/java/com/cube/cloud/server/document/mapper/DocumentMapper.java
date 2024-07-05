package com.cube.cloud.server.document.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.server.document.entity.Document;
import com.cube.cloud.server.document.model.DocumentPageInput;
import com.cube.cloud.server.document.query.DocumentDetailsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:25
 */
@Mapper
public interface DocumentMapper extends BaseMapper<Document> {

    /**
     * 根据id查询文案详情信息
     * @param documentId 文案id
     * @return DocumentDetailsQuery
     */
    DocumentDetailsQuery getDetails(Long documentId);

    /**
     * 分页查询
     * @param page 分页信息
     * @param input 查询输出信息
     * @return Page<DocumentDetailsQuery>
     */
    Page<DocumentDetailsQuery> getPage(@Param("page") Page<?> page, @Param("input") DocumentPageInput input);
}
