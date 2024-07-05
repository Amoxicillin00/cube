package com.cube.cloud.server.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.constants.StatusCode;
import com.cube.cloud.core.enums.FileTypeEnum;
import com.cube.cloud.core.exception.BaseException;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.file.storage.StorageClient;
import com.cube.cloud.core.file.storage.model.FileObject;
import com.cube.cloud.core.util.AESUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.server.file.entity.File;
import com.cube.cloud.server.file.mapper.FileMapper;
import com.cube.cloud.server.file.model.FileInput;
import com.cube.cloud.server.file.model.FileOutput;
import com.cube.cloud.server.file.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * 文件接口实现
 * @author Long
 * @date 2023-01-10 16:00
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private StorageClient storageClient;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public FileOutput upload(MultipartFile multipartFile, FileInput input) {
        logger.info("文件上传请求参数：{}，{}", multipartFile, input);
        ExceptionUtils.check(StringUtils.isEmpty(multipartFile.getOriginalFilename()), "文件为空");
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            //文件名称处理。
            String builder = originalFilename + System.currentTimeMillis() + new Random().nextInt(10000);
            String path = "";
            String filePath = path + java.io.File.separator;
            String fileName = AESUtils.MD5(builder);
            assert originalFilename != null;
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
            FileObject fileObject = storageClient.save(storageClient.getDefaultBucketName(), filePath + fileName + extensionName, multipartFile.getInputStream());
            logger.info("文件保存FileObject：{}", fileObject);
            File file = MapUtils.map(input, File.class);
            //文件原名称
            file.setExtensionName(fileObject.getFileInfo().getExtensionName());
            file.setName(originalFilename);
            file.setSize(fileObject.getFileInfo().getLengthString());
            file.setAccessUrl(fileObject.getAccessUrl());
            file.setAbsolutePath(fileObject.getFileInfo().getFullPath());
            file.setDelete(false);
            file.setType(FileTypeEnum.getFileTypeCode(multipartFile.getContentType()));
            file.setCreatedTime(LocalDateTime.now());
            file.setModifiedTime(LocalDateTime.now());
            file.setRemark(multipartFile.getContentType());
            logger.info("文件保存File：{}", file);
            this.save(file);
            return MapUtils.map(file, FileOutput.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("文件保存失败");
        }
    }
}
