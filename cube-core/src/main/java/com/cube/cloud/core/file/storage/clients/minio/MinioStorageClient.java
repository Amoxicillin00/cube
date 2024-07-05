package com.cube.cloud.core.file.storage.clients.minio;

import com.cube.cloud.core.file.storage.AbstractStorageClient;
import com.cube.cloud.core.file.storage.model.*;
import com.cube.cloud.core.util.StringUtils;
import io.minio.ErrorCode;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.messages.Item;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-10 14:09
 */
@Getter
public class MinioStorageClient extends AbstractStorageClient<MinioBucket> {

    /**
     * 通道id
     */
    public static final String CHANNEL_ID = "Minio";

    /**
     * 通道名称
     */
    public static final String CHANNEL_NAME = "Minio 文件服务器";

    /**
     * 服务器地址
     */
    private final String serverUrl;

    /**
     * accessKey
     */
    private final String accessKey;

    /**
     * secretKey
     */
    private final String secretKey;

    protected final MinioBigClient minioClient;



    /**
     * 获取访问Url
     * @param bucketName 分区名称
     * @param fileInfo 文件信息
     * @return String
     */
    public String getAccessUrl(String bucketName, FileInfo fileInfo) {
        return this.getPath(this.getEndpoint(), bucketName, fileInfo.getFullPath());
    }

    /**
     * 设置文件选项
     * @param options 选项
     * @param request 请求
     */
    protected void setHeaders(PutObjectOptions options, FileStorageRequest request) {
        String contentType = this.getFileContentType(request.getFileInfo().getExtensionName());
        if (StringUtils.isNotNullOrBlank(contentType)) {
            options.setContentType(contentType);
        }
        String contentDisposition = this.getContentDisposition(request);
        if (StringUtils.isNotNullOrBlank(contentDisposition)) {
            Map<String, String> headers = new HashMap<>(1);
            headers.put(CONTENT_DISPOSITION, contentDisposition);
            options.setHeaders(headers);
        }
    }

    /**
     * 创建客户端
     * @return MinioBigClient
     */
    private MinioBigClient createMinioClient() {
        try {
            return new MinioBigClient(this.getServerUrl(), this.getAccessKey(), this.getSecretKey());
        } catch (InvalidEndpointException e) {
            //TODO 抛出异常
            System.out.println("抛出异常！无效的服务器地址：" + this.getServerUrl());
            return null;
            //throw ExceptionUtils.throwConfigureException("无效的服务器地址:" + this.getServerUrl());
        } catch (InvalidPortException e) {
            //TODO 抛出异常
            System.out.println("抛出异常！无效的服务器端口：" + this.getServerUrl());
            return null;
            //throw ExceptionUtils.throwConfigureException("无效的服务器端口:" + this.getServerUrl());
        }
    }

    /**
     *MinioStorageClient
     * @param properties 属性
     */
    public MinioStorageClient(MinioStorageClientProperties properties) {
        this(properties.getEndpoint(),
                properties.getDefaultBucketName(),
                properties.getServerUrl(),
                properties.getAccessKey(),
                properties.getSecretKey());
        this.setStorageClientProperties(properties);
    }

    public MinioStorageClient(String endpoint, String defaultBucketName, String serverUrl,
                              String accessKey, String secretKey) {
        super(endpoint, defaultBucketName);
        this.serverUrl = serverUrl;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.minioClient = this.createMinioClient();
    }


    @Override
    public String getChannelId() {
        return CHANNEL_ID;
    }

    @Override
    public String getChannelName() {
        return CHANNEL_NAME;
    }

    @Override
    public boolean existBucket(String bucketName) {
        //TODO 抛出异常
        System.out.println("抛出异常！bucketName");
        //ExceptionUtils.checkNotNullOrBlank(bucketName, "bucketName");
        try {
            return this.minioClient.bucketExists(bucketName);
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return false;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        }
    }

    @Override
    public MinioBucket createBucket(String bucketName) {
        try {
            this.minioClient.makeBucket(bucketName);
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return null;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        }
        return new MinioBucket(bucketName);
    }

    @Override
    public MinioBucket getBucket(String bucketName) {
        if (this.existBucket(bucketName)) {
            return new MinioBucket(bucketName);
        }
        return null;
    }

    @Override
    public FileObject save(FileStorageRequest request) throws Exception {
        //TODO 抛出异常
        System.out.println("抛出异常！request");
        //ExceptionUtils.checkNotNull(request, "request");
        String bucketName = this.checkBucketName(request.getBucketName());
        if (!this.existBucket(bucketName)) {
            this.createBucket(bucketName);
        }
        InputStream inputStream = null;
        try {
            FileStream stream = this.readRequestStream(request);
            long size = stream.getSize();
            inputStream = stream.getInputStream();
            PutObjectOptions options = new PutObjectOptions(size, -1L);
            this.setHeaders(options, request);
            this.minioClient.putObject(bucketName, request.getFileInfo().getFullPath(), inputStream, options);
            FileObject fileObject = new FileObject();
            fileObject.setFileInfo(request.getFileInfo());
            fileObject.getFileInfo().setLength(size);
            fileObject.setUrl(request.getFileInfo().getFullPath());
            fileObject.setAccessUrl(this.getAccessUrl(bucketName, request.getFileInfo().getFullPath()));
            return fileObject;
        } finally {
            IOUtils.closeQuietly(request.getInputStream());
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Override
    public FileStorageObject getFile(String bucketName, String fullPath) {
        bucketName = this.checkBucketName(bucketName);
        //TODO 抛出异常
        System.out.println("抛出异常！fullPath");
        //ExceptionUtils.checkNotNullOrBlank(fullPath, "fullPath");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            inputStream = this.minioClient.getObject(bucketName, fullPath);
            long size = this.writeOutputStream(inputStream, outputStream);
            FileInfo fileInfo = new FileInfo(fullPath, true, outputStream.size());
            FileStorageObject storageObject = new FileStorageObject();
            storageObject.setInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
            storageObject.setAccessUrl(this.getAccessUrl(bucketName, fullPath));
            storageObject.setUrl(fullPath);
            storageObject.setFileInfo(fileInfo);
            return storageObject;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().errorCode().equals(ErrorCode.NO_SUCH_OBJECT)) {
                return null;
            }
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return null;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return null;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Override
    public boolean existFile(String bucketName, String fullPath) {
        bucketName = this.checkBucketName(bucketName);
        //TODO 抛出异常
        System.out.println("抛出异常！fullPath");
        //ExceptionUtils.checkNotNullOrBlank(fullPath, "fullPath");
        try {
            ObjectStat stat = this.minioClient.statObject(bucketName, fullPath);
            return stat != null;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().errorCode().equals(ErrorCode.NO_SUCH_OBJECT)) {
                return false;
            }
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return false;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return false;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        }
    }

    @Override
    public String getAccessUrl(String bucketName, String fullPath) {
        bucketName = this.checkBucketName(bucketName);
        //TODO 抛出异常
        System.out.println("抛出异常！fullPath");
        //ExceptionUtils.checkNotNullOrBlank(fullPath, "fullPath");
        FileInfo fileInfo = new FileInfo(fullPath, true);
        return this.getAccessUrl(bucketName, fileInfo);
    }

    @Override
    public void deleteFile(String bucketName, String fullPath) {
        bucketName = this.checkBucketName(bucketName);
        //TODO 抛出异常
        System.out.println("抛出异常！fullPath");
        //ExceptionUtils.checkNotNullOrBlank(fullPath, "fullPath");
        try {
            FileInfo fileInfo = new FileInfo(fullPath, true);
            this.minioClient.removeObject(bucketName, fileInfo.getFullPath());
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        }
    }

    @Override
    public List<FileObject> getList(String bucketName, String prefix) {
        bucketName = this.checkBucketName(bucketName);
        try {
            Iterable<Result<Item>> items = this.minioClient.listObjects(bucketName, prefix);
            List<FileObject> fileObjects = new ArrayList<>(16);
            for (Result<Item> item : items) {
                Item i = item.get();
                FileObject fileObject = new FileObject();
                FileInfo fileInfo = new FileInfo(i.objectName(), !i.isDir());
                fileInfo.setLength(i.size());
                fileObject.setFileInfo(fileInfo);
                fileObject.setUrl(i.objectName());
                fileObject.setAccessUrl(this.getAccessUrl(bucketName, i.objectName()));
                fileObjects.add(fileObject);
            }
            return fileObjects;
        } catch (Exception e) {
            //TODO 抛出异常
            System.out.println("抛出异常：" + e.getMessage());
            return null;
            //throw ExceptionUtils.throwSystemException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return "启用 FileStorage " + this.getChannelName()
                + " 默认分区:" + this.getDefaultBucketName()
                + " 访问根路径:" + this.getEndpoint()
                + " 服务器地址:" + this.getServerUrl();
    }
}
