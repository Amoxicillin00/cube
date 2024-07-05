package com.cube.cloud.core.application.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 15:40
 */
@Data
public class BasePageOutput<T> implements Serializable {

    private static final long serialVersionUID = 639564562425305146L;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页，默认为第1页")
    private long current = 1;

    /**
     * 每页显示数，默认 10，最大 200
     */
    @ApiModelProperty("每页显示数，默认 10，最大 200")
    private long size = 10;

    /**
     * 查询总数
     */
    @ApiModelProperty("查询总数")
    private long total = 0;

    /**
     * 数据列表
     */
    @ApiModelProperty("数据列表")
    private List<T> records = Collections.emptyList();

    /**
     * 无参构造函数
     */
    public BasePageOutput() {
    }

    /**
     * 有参构造函数
     * @param current 当前页
     * @param size 查询数
     */
    public BasePageOutput(long current, long size) {
        this(current, size, 0);
    }

    /**
     * 有参构造函数
     * @param current 当前页
     * @param size 查询数
     * @param total 查询总数
     */
    public BasePageOutput(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        } else {
            this.current = 1;
        }
        this.size = size;
        this.total = total;
    }

    /**
     * 静态构造方法
     * @param current 当前页
     * @param size 查询数
     * @return BasePageOutput
     */
    public static <T> BasePageOutput<T> build(long current, long size) {
        return build(current, size, 0);
    }

    /**
     * 今天构造方法
     * @param current 当前页
     * @param size 查询数
     * @param total 查询总数
     * @return BasePageOutput
     */
    public static <T> BasePageOutput<T> build(long current, long size, long total) {
        return new BasePageOutput<>(current, size, total);
    }
}
