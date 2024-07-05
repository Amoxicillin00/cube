package com.cube.cloud.server.file.service;

import com.cube.cloud.server.file.model.FileInput;
import com.cube.cloud.server.file.model.FileOutput;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * 文件接口
 * @author Long
 * @date 2023-01-10 16:00
 */
public interface FileService {

    /**
     * 文件上传
     * @param file 文件信息
     * @param input 文件输入参数
     * @return FileOutput
     */
    FileOutput upload(MultipartFile file, FileInput input);
}
