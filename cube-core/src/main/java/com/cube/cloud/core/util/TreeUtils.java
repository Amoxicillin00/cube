package com.cube.cloud.core.util;

import cn.hutool.core.collection.CollUtil;
import com.cube.cloud.core.application.model.BaseTreeOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-08-15 11:05
 */
public class TreeUtils {


    /**
     * 列表树化
     * @param parentId 父级id
     * @param outputList 列表
     * @param <T> 子类
     * @return 树化后的列表
     */
    public static <T extends BaseTreeOutput> List<T> buildBaseTree(Serializable parentId, List<T> outputList) {
        List<T> childList = new ArrayList<>();
        if (CollUtil.isNotEmpty(outputList)) {
            // 子节点列表
            Iterator<T> iterator = outputList.iterator();
            while (iterator.hasNext()) {
                T child = iterator.next();
                if (Objects.equals(parentId, child.getParentId())) {
                    // 设置子节点
                    child.setChildList(buildBaseTree(child.getId(), outputList));
                    childList.add(child);
                    // 减少递归复杂度
                    //iterator.remove();
                }
            }
        }
        return childList;
    }

    /**
     * 列表树化
     * @param parentId 父级id
     * @param objects 列表
     * @param clazz 树化后的类型
     * @param <T> 子类
     * @return 树化后的列表
     */
    public static <T extends BaseTreeOutput> List<T> buildBaseTree(Serializable parentId, List<?> objects, Class<T> clazz) {
        List<T> treeOutputs = MapUtils.mapForList(objects, clazz);
        return buildBaseTree(parentId, treeOutputs);
    }
}
