package com.cube.cloud.server.file.controller;

import com.cube.cloud.server.file.model.FileInput;
import com.cube.cloud.server.file.model.FileOutput;
import com.cube.cloud.server.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * 文件管理
 * @author Long
 * @date 2023-01-10 15:52
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public FileOutput upload(@RequestPart @RequestParam("file") MultipartFile file, @Valid FileInput input) {
        return this.fileService.upload(file, input);
    }
}
