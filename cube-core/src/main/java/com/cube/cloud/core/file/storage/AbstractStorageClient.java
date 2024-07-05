package com.cube.cloud.core.file.storage;

import cn.hutool.core.util.StrUtil;
import com.cube.cloud.core.application.model.AbstractBucket;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.file.storage.model.FileObject;
import com.cube.cloud.core.file.storage.model.FileStorageRequest;
import com.cube.cloud.core.file.storage.model.FileStream;
import com.cube.cloud.core.file.storage.properties.AbstractStorageClientProperties;
import com.cube.cloud.core.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 存储抽象
 * @author Long
 * @date 2023-01-09 11:49
 */
public abstract class AbstractStorageClient<T extends AbstractBucket> implements StorageClient {

    private static final Logger logger = LoggerFactory.getLogger(AbstractStorageClient.class);

    /**
     * 终节点
     */
    private final String endpoint;

    /**
     * 默认分区名称
     */
    private final String defaultBucketName;

    /**
     * 写入块大小
     */
    private int writeBlockSize = 2048;

    /**
     * 读取块大小
     */
    private int readBlockSize = 4096;

    /**
     * Url 分隔符
     */
    public static final char URL_SEPARATOR = '/';

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    private static final Map<String, String> EXTENSION_CONTENT_TYPE_MAP = new HashMap<>(16);

    static {
        EXTENSION_CONTENT_TYPE_MAP.put("jpg", "image/jpg");
        EXTENSION_CONTENT_TYPE_MAP.put("png", "image/png");
        EXTENSION_CONTENT_TYPE_MAP.put("bmp", "image/bmp");
        EXTENSION_CONTENT_TYPE_MAP.put("tif", "image/tif");
        EXTENSION_CONTENT_TYPE_MAP.put("gif", "image/gif");
        EXTENSION_CONTENT_TYPE_MAP.put("jpeg", "image/jpeg");
        EXTENSION_CONTENT_TYPE_MAP.put("tiff", "image/tiff");
        EXTENSION_CONTENT_TYPE_MAP.put("dib", "image/dib");
        EXTENSION_CONTENT_TYPE_MAP.put("html", "text/html");
        EXTENSION_CONTENT_TYPE_MAP.put("xml", "text/xml");
        EXTENSION_CONTENT_TYPE_MAP.put("plain", "text/plain");
        EXTENSION_CONTENT_TYPE_MAP.put("txt", "text/plain");
        EXTENSION_CONTENT_TYPE_MAP.put("json", "application/json");
        EXTENSION_CONTENT_TYPE_MAP.put("pdf", "application/pdf");
    }


    public AbstractStorageClient(String endpoint, String defaultBucketName) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(endpoint), "url根路径不能为空");

        this.endpoint = StringUtils.removeStart(StringUtils.removeEnd(endpoint, URL_SEPARATOR), URL_SEPARATOR)
                .toLowerCase();
        if (defaultBucketName == null) {
            defaultBucketName = "";
        } else {
            defaultBucketName = this.checkBucketName(defaultBucketName);
        }
        this.defaultBucketName = defaultBucketName.trim().toLowerCase();
    }

    /**
     * 实例化
     * @param properties 属性
     */
    public AbstractStorageClient(AbstractStorageClientProperties properties) {
        this(properties.getEndpoint(), properties.getDefaultBucketName());
    }


    /**
     * 设置属性
     * @param properties 属性
     */
    protected void setStorageClientProperties(AbstractStorageClientProperties properties) {
        this.setReadBlockSize(properties.getReadBlockSize());
        this.setWriteBlockSize(properties.getWriteBlockSize());
    }


    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override
    public String getDefaultBucketName() {
        return this.defaultBucketName;
    }

    @Override
    public abstract T createBucket(String bucketName);

    @Override
    public abstract T getBucket(String bucketName);

    @Override
    public final FileObject save(String bucketName, String fullPath, InputStream input) throws Exception {
        FileStorageRequest request = new FileStorageRequest(bucketName, input, fullPath);
        return this.save(request);
    }

    @Override
    public final List<FileObject> getList(String bucketName) {
        return this.getList(bucketName, null);
    }

    /**
     * 检查分区名称
     * @param bucketName 分区名称
     * @return 分区名称
     */
    protected String checkBucketName(String bucketName) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(bucketName), "分区名称分区名称不能为空");
        return bucketName;
    }

    /**
     * 获取路径地址
     * @param args 参数集合
     * @return 路径地址
     */
    protected final String getPath(String... args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (i > 0) {
                builder.append(URL_SEPARATOR);
            }
            builder.append(arg);
        }
        return builder.toString();
    }

    /**
     * 写入到输出流
     * @param inputStream  输入流
     * @param outputStream 输出流
     * @return long
     * @throws IOException IO异常
     */
    protected final long writeOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        long size = 0;
        int byteCount;
        byte[] bytes = new byte[this.getWriteBlockSize()];
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
            size += byteCount;
        }
        return size;
    }

    /**
     * 获取文件ContentType
     * @param ext 扩展名
     * @return String
     */
    public String getFileContentType(String ext) {
        if (ext == null) {
            return "application/octet-stream";
        }
        String contentType = EXTENSION_CONTENT_TYPE_MAP.get(ext.toLowerCase().trim());
        if (StringUtils.isNotNullOrBlank(contentType)) {
            return contentType;
        }
        return "application/octet-stream";
    }

    /**
     * 获取内容 Disposition
     * @param request 请求
     * @return String
     */
    protected String getContentDisposition(FileStorageRequest request) {
        if (StringUtils.isNotNullOrBlank(request.getFileInfo().getName())) {
            try {
                String fileName = request.getFileInfo().getName() + "." + request.getFileInfo().getExtensionName();
                return "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        return "";
    }

    /**
     * 读取请求流
     * @param request 请求
     * @return Map<Long, InputStream>
     * @throws IOException IO异常
     */
    protected FileStream readRequestStream(FileStorageRequest request) throws IOException {
        FileStream stream = new FileStream();
        if (request.getFileInfo().getLength() > 0L) {
            stream.setInputStream(request.getInputStream());
            stream.setSize(request.getFileInfo().getLength());
        } else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                stream.setSize(this.writeOutputStream(request.getInputStream(), outputStream));
                if (request.getInputStream() instanceof ByteArrayInputStream) {
                    ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) request.getInputStream();
                    byteArrayInputStream.reset();
                    stream.setInputStream(byteArrayInputStream);
                } else {
                    stream.setInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
                }
            } finally {
                IOUtils.closeQuietly(outputStream);
            }
        }
        return stream;
    }

    /**
     * 获取读取块大小
     * @return int
     */
    @Override
    public int getReadBlockSize() {
        return this.readBlockSize;
    }

    /**
     * 设置读块大小
     * @param readBlockSize 读块大小
     */
    @Override
    public void setReadBlockSize(int readBlockSize) {
        this.readBlockSize = readBlockSize;
    }

    /**
     * 获取写块大小
     * @return int
     */
    @Override
    public int getWriteBlockSize() {
        return this.writeBlockSize;
    }

    /**
     * 设置写块大小
     * @param writeBlockSize 写块大小
     */
    @Override
    public void setWriteBlockSize(int writeBlockSize) {
        this.writeBlockSize = writeBlockSize;
    }

    @Override
    public String toString() {
        return "AbstractStorageClient{" +
                "endpoint='" + endpoint + '\'' +
                ", defaultBucketName='" + defaultBucketName + '\'' +
                ", writeBlockSize=" + writeBlockSize +
                ", readBlockSize=" + readBlockSize +
                '}';
    }

}
