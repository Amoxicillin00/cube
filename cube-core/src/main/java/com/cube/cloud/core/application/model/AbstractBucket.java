package com.cube.cloud.core.application.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * 存储分区抽象
 * @author Long
 * @date 2023-01-09 11:52
 */
@ToString(callSuper = true)
@Data
public abstract class AbstractBucket implements Serializable {

    private static final long serialVersionUID = -639800548174076671L;

    /**
     * 分区名称
     */
    private final String name;
    /**
     * 位置
     */
    private String location;

    /**
     * 创建时间
     */
    private Date creationDate;



    protected AbstractBucket(String name) {
        this.name = name;
    }

    public AbstractBucket(String name, String location, Date creationDate) {
        this.name = name;
        this.location = location;
        this.creationDate = creationDate;
    }
}
