package com.cube.cloud.core.file.storage.clients.minio;

import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 多部份上传选项
 * @author Long
 * @date 2023-01-10 11:53
 */
@Getter
public class MinioMultiPartUploadOptions implements Serializable {

    private static final long serialVersionUID = -3356659319244128378L;

    private final String bucketName;

    private final String objectName;

    private final String uploadId;

    private final long objectSize;

    private final long partSize;

    private final int partCount;

    private final Map<String, String> ssecHeaders;

    public MinioMultiPartUploadOptions(String bucketName, String objectName, String uploadId, long objectSize, long partSize, Map<String, String> ssecHeaders) {
        this.bucketName = bucketName;
        this.objectName = objectName;
        this.uploadId = uploadId;
        this.objectSize = objectSize;
        this.partSize = partSize;
        this.partCount = (int) Math.ceil((double) objectSize / partSize);
        this.ssecHeaders = ssecHeaders;
    }
}
