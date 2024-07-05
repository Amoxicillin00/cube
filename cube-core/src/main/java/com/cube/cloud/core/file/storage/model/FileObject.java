package com.cube.cloud.core.file.storage.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 文件对象
 * @author Long
 * @date 2023-01-09 14:38
 */
@Data
@ToString(callSuper = true)
public class FileObject implements Serializable {

    private static final long serialVersionUID = -2658878913889924251L;

    /**
     *文件信息
     */
    private FileInfo fileInfo;

    /**
     * url
     */
    private String url;

    /**
     * 访问文件夹
     */
    private String accessUrl;

}
