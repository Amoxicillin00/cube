package com.cube.cloud.core.file.storage;

import com.cube.cloud.core.application.channel.Channel;
import com.cube.cloud.core.application.model.AbstractBucket;
import com.cube.cloud.core.file.storage.model.FileObject;
import com.cube.cloud.core.file.storage.model.FileStorageObject;
import com.cube.cloud.core.file.storage.model.FileStorageRequest;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 存储客户端
 * @author Long
 * @date 2023-01-09 12:07
 */
public interface StorageClient extends Channel {


    /**
     * 获取节点，如根url
     * @return String
     */
    String getEndpoint();

    /**
     * 获取默认分区名称
     * @return String
     */
    String getDefaultBucketName();

    /**
     * 获取读取块大小
     * @return int
     */
    int getReadBlockSize();

    /**
     * 设置读块大小
     * @param readBlockSize 读块大小
     */
    void setReadBlockSize(int readBlockSize);

    /**
     * 获取写块大小
     * @return int
     */
    int getWriteBlockSize();

    /**
     * 设置写块大小
     * @param writeBlockSize 写块大小
     */
    void setWriteBlockSize(int writeBlockSize);

    /**
     * 是否存在分区
     * @param bucketName 分区名称
     * @return boolean
     */
    boolean existBucket(String bucketName);

    /**
     * 创建分区
     * @param bucketName 分区名称
     * @return AbstractBucket
     */
    AbstractBucket createBucket(String bucketName);

    /**
     * 获取分区
     * @param bucketName 分区名称
     * @return 不存在则返回 null
     */
    AbstractBucket getBucket(String bucketName);

    /**
     * 保存文件
     * @param bucketName 分区名称
     * @param fullPath 完整的路径
     * @param input 输入流
     * @return FileObject
     * @throws Exception 异常
     */
    FileObject save(String bucketName, String fullPath, InputStream input) throws Exception;

    /**
     * 保存文件
     * @param request 保存请求
     * @return FileObject
     * @throws Exception 异常
     */
    FileObject save(FileStorageRequest request) throws Exception;

    /**
     * 获取文件
     * @param bucketName 分区名称
     * @param fullPath 完整的路径
     * @return FileStorageObject
     */
    FileStorageObject getFile(String bucketName, String fullPath);

    /**
     * 是否存在文件
     * @param bucketName 分区名称
     * @param fullPath   完整的路径
     * @return boolean
     */
    boolean existFile(String bucketName, String fullPath);

    /**
     * 获取访问Url
     * @param bucketName 分区名称
     * @param fullPath   完整的路径
     * @return String
     */
    String getAccessUrl(String bucketName, String fullPath);

    /**
     * 删除文件
     * @param bucketName 分区名称
     * @param fullPath   完整的路径
     */
    void deleteFile(String bucketName, String fullPath);

    /**
     * 从分区获取文件列表
     * @param bucketName 分区名称
     * @return List<FileObject>
     */
    List<FileObject> getList(String bucketName);

    /**
     * 获取文件列表
     * @param bucketName 分区名称
     * @param prefix 前缀
     * @return List<FileObject>
     */
    List<FileObject> getList(String bucketName, String prefix);
}
