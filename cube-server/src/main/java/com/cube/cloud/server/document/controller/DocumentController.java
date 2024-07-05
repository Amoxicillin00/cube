package com.cube.cloud.server.document.controller;

import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.server.document.model.*;
import com.cube.cloud.server.document.service.DocumentService;
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
 * 文档服务
 * @author Long
 * @date 2023-01-03 17:22
 */
@RestController
@RequestMapping("/document")
@Api(tags = "文档服务")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    /**
     * 添加
     * @param input 添加参数
     */
    @ApiOperation("添加")
    @PostMapping("/add")
    public void add(@Valid @RequestBody DocumentAddInput input) {
        this.documentService.add(input);
    }

    /**
     * 删除
     * @param input 主键id
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public void delete(@Valid @RequestBody BaseIdInput input) {
        this.documentService.delete(input);
    }

    /**
     * 修改
     * @param input 修改参数
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public void update(@Valid @RequestBody DocumentModifyInput input) {
        this.documentService.update(input);
    }

    /**
     * 详情
     * @param input 主键id
     * @return DocumentDetailsOutput
     */
    @ApiOperation("详情")
    @PostMapping("/details")
    public DocumentDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return this.documentService.getDetails(input);
    }

    /**
     * 列表查询
     * @return List<DocumentItemOutput>
     */
    @ApiOperation("列表查询")
    @PostMapping("/list")
    public List<DocumentItemOutput> list() {
        return this.documentService.getList();
    }

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<DocumentItemOutput>
     */
    @ApiOperation("分页查询")
    @PostMapping("/page")
    public BasePageOutput<DocumentItemOutput> page(@Valid @RequestBody DocumentPageInput input) {
        return this.documentService.getPage(input);
    }

}
