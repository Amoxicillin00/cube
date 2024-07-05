package com.cube.cloud.core.file.storage.clients.local;

import com.cube.cloud.core.file.storage.AbstractStorageClient;
import com.cube.cloud.core.file.storage.model.FileInfo;
import com.cube.cloud.core.file.storage.model.FileObject;
import com.cube.cloud.core.file.storage.model.FileStorageObject;
import com.cube.cloud.core.file.storage.model.FileStorageRequest;
import com.cube.cloud.core.util.StringUtils;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-10 14:51
 */
@Getter
public class LocalStorageClient extends AbstractStorageClient<LocalBucket> {

    /**
     * 通道id
     */
    public static final String CHANNEL_ID = "Local";

    /**
     * 通道名称
     */
    public static final String CHANNEL_NAME = "本地储存";

    private final String rootFilePath;


    /**
     * LocalStorageClient
     * @param endpoint 终结点
     * @param defaultBucketName 默认分区
     * @param rootFilePath 根目录
     */
    public LocalStorageClient(String endpoint, String defaultBucketName, String rootFilePath) {
        super(endpoint, defaultBucketName);
        if (StringUtils.isNullOrBlank(rootFilePath)) {
            this.rootFilePath = getDefaultLocalPath();
        } else {
            this.rootFilePath = StringUtils.removeEnd(rootFilePath.replace('\\', URL_SEPARATOR), URL_SEPARATOR);
        }
    }

    /**
     * LocalStorageClient
     * @param properties 属性
     */
    public LocalStorageClient(LocalStorageClientProperties properties) {
        this(properties.getEndpoint(),
                properties.getDefaultBucketName(),
                properties.getRootFilePath());
        this.setStorageClientProperties(properties);
    }

    public static String getDefaultLocalPath() {
        return "/static";
        //return ResourceUtils.getResourceRootPath() + "/static";
    }

    /**
     * 创建分区
     * @param bucketName 分区名称
     * @param file 文件
     * @return LocalBucket
     */
    private LocalBucket createBucket(String bucketName, File file) {
        LocalBucket bucket = new LocalBucket(bucketName);
        bucket.setLocation(file.getPath());
        bucket.setCreationDate(new Date(file.lastModified()));
        return bucket;
    }

    /**
     *获取文件路径
     * @param bucketName 分区名称
     * @param fileInfo 文件信息
     * @return String
     */
    private String getFilePath(String bucketName, FileInfo fileInfo) {
        return this.getPath(this.getRootFilePath(), bucketName, fileInfo.getFullPath());
    }

    /**
     * 获取访问Url
     * @param bucketName 分区名称
     * @param fileInfo 文件信息
     * @return String
     */
    private String getAccessUrl(String bucketName, FileInfo fileInfo) {
        return this.getPath(this.getEndpoint(), bucketName, fileInfo.getFullPath());
    }

    /**
     * 获取本地文件
     * @param bucketName 分区名称
     * @param fullPath 路径
     * @return File
     */
    private File getLocalFile(String bucketName, String fullPath) {
        FileInfo fileInfo = new FileInfo(fullPath, true);
        String filePath = this.getFilePath(bucketName, fileInfo);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file;
        }
        return null;
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
        File dir = new File(this.getPath(this.getRootFilePath(), bucketName));
        return dir.exists() && dir.isDirectory();
    }

    @Override
    public LocalBucket createBucket(String bucketName) {
        //TODO 抛出异常
        System.out.println("抛出异常！bucketName");
        //ExceptionUtils.checkNotNullOrBlank(bucketName, "bucketName");
        File dir = new File(this.getPath(this.getRootFilePath(), bucketName));
        if (dir.exists() && dir.isDirectory()) {
            //TODO 抛出异常
            System.out.println("抛出异常！分区" + bucketName + "已经存在");
            //ExceptionUtils.throwValidationException("分区 " + bucketName + " 已经存在。");
        }
        dir.mkdirs();
        return this.createBucket(bucketName, dir);
    }

    @Override
    public LocalBucket getBucket(String bucketName) {
        //TODO 抛出异常
        System.out.println("抛出异常！bucketName");
        //ExceptionUtils.checkNotNullOrBlank(bucketName, "bucketName");
        File dir = new File(this.getPath(this.getRootFilePath(), bucketName));
        if (dir.exists() && dir.isDirectory()) {
            return this.createBucket(bucketName, dir);
        }
        return null;
    }

    @Override
    public FileObject save(FileStorageRequest request) throws Exception {
        //TODO 抛出异常
        System.out.println("抛出异常！request");
        //ExceptionUtils.checkNotNull(request, "request");
        FileOutputStream outputStream = null;
        try {
            String savePath = this.getFilePath(this.checkBucketName(request.getBucketName()), request.getFileInfo());
            File file = new File(savePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            file = new File(this.getPath(this.getRootFilePath(), request.getBucketName(),
                    request.getFileInfo().getPath()));
            if (!file.exists()) {
                file.mkdirs();
            } else {
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
            }
            file = new File(savePath);
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            long fileSize = this.writeOutputStream(request.getInputStream(), outputStream);
            FileObject fileObject = new FileObject();
            fileObject.setFileInfo(request.getFileInfo());
            fileObject.getFileInfo().setLength(fileSize);
            fileObject.setUrl(request.getFileInfo().getFullPath());
            fileObject.setAccessUrl(this.getAccessUrl(request.getBucketName(), request.getFileInfo()));
            return fileObject;

        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(request.getInputStream());
        }
    }

    @Override
    public FileStorageObject getFile(String bucketName, String fullPath) {
        FileInfo fileInfo = new FileInfo(fullPath, true);
        String filePath = this.getFilePath(this.checkBucketName(bucketName), fileInfo);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            FileStorageObject fileStorageObject = new FileStorageObject();
            fileStorageObject.setFileInfo(new FileInfo(fullPath, true));
            fileStorageObject.setAccessUrl(this.getAccessUrl(bucketName, fileInfo));
            fileStorageObject.getFileInfo().setLength(file.length());
            fileStorageObject.setUrl(fileInfo.getFullPath());
            FileInputStream input;
            try {
                input = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                //TODO 抛出异常
                System.out.println("抛出异常！文件不存在");
                return null;
                //throw ExceptionUtils.throwValidationException("文件不存在。");
            }
            fileStorageObject.setInputStream(input);
            return fileStorageObject;
        }
        return null;
    }

    @Override
    public boolean existFile(String bucketName, String fullPath) {
        File file = this.getLocalFile(bucketName, fullPath);
        return file != null;
    }

    @Override
    public String getAccessUrl(String bucketName, String fullPath) {
        bucketName = this.checkBucketName(bucketName);
        FileInfo fileInfo = new FileInfo(fullPath, true);
        return this.getAccessUrl(bucketName, fileInfo);
    }

    @Override
    public void deleteFile(String bucketName, String fullPath) {
        File file = this.getLocalFile(bucketName, fullPath);
        if (file != null) {
            file.delete();
        }
    }

    @Override
    public List<FileObject> getList(String bucketName, String prefix) {
        bucketName = this.checkBucketName(bucketName);
        File dir;
        String url;
        if (StringUtils.isNullOrBlank(prefix)) {
            dir = new File(this.getPath(this.getRootFilePath(), bucketName));
            url = Character.toString(URL_SEPARATOR);
        } else {
            url = StringUtils.removeEnd(StringUtils.removeStart(prefix, URL_SEPARATOR), URL_SEPARATOR);
            dir = new File(this.getPath(this.getRootFilePath(), bucketName, prefix));
        }
        List<FileObject> fileObjects = new ArrayList<>();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
            for (File file : files) {
                FileObject fileObject = new FileObject();
                FileInfo fileInfo;
                if (file.isDirectory()) {
                    fileInfo = new FileInfo(this.getPath(url, file.getName()), false, 0);
                } else {
                    fileInfo = new FileInfo(this.getPath(url, file.getName()), true, file.length());
                }
                fileObject.setFileInfo(fileInfo);
                fileObject.setUrl(fileInfo.getFullPath());
                fileObject.setAccessUrl(this.getAccessUrl(bucketName, fileInfo));
                fileObjects.add(fileObject);
            }
        }
        return fileObjects;
    }

    @Override
    public String toString() {
        return super.toString() + " 文件保存根路径:" + this.getRootFilePath();
    }
}
