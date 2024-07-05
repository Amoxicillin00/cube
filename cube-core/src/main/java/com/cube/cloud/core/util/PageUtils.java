package com.cube.cloud.core.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.core.application.model.BasePageOutput;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 17:04
 */
public class PageUtils {

    /**
     * 构建分页结构
     * @param page mybatis-plus分页对象
     * @param clazz 结构类型
     * @param <T> 结构类型
     * @return 分页结果
     */
    public static <T> BasePageOutput<T> buildBasePage(Page<?> page, Class<T> clazz) {
        BasePageOutput<T> basePageOutput = BasePageOutput.build(page.getCurrent(), page.getSize(), page.getTotal());
        List<T> list = MapUtils.mapForList(page.getRecords(), clazz);

        basePageOutput.setRecords(list);
        return basePageOutput;
    }

}
