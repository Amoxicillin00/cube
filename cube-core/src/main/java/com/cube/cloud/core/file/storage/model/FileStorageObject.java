package com.cube.cloud.core.file.storage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-09 14:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileStorageObject extends FileObject{

    private static final long serialVersionUID = 7136838301158644425L;

    /**
     * 输入流
     */
    private InputStream inputStream;
}
