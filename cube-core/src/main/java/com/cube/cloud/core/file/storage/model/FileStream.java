package com.cube.cloud.core.file.storage.model;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-09 16:50
 */
@Data
public class FileStream implements Serializable {

    private static final long serialVersionUID = -8218855214302886803L;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 输入流
     */
    private InputStream inputStream;
}
