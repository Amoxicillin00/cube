package com.cube.cloud.core.util;

import com.cube.cloud.core.function.action.FunctionTwoAction;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTransformers;
import org.modelmapper.convention.NamingConventions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * map序列工具
 * @author Long
 * @date 2023-01-04 16:42
 */
public class MapUtils {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration()
                .setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR)
                .setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
                .setSourceNameTransformer(NameTransformers.JAVABEANS_ACCESSOR)
                .setDestinationNameTransformer(NameTransformers.JAVABEANS_MUTATOR)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * 加载
     * @param source 源对象
     * @param target 被赋值的目标对象
     */
    public static void mapForLoad(Object source, Object target) {
        if (source != null && target != null) {
            MODEL_MAPPER.map(source, target);
        }
    }

    /**
     * 转换为列表
     * @param sourceList 源列表对象
     * @param targetClass 目标类型对象
     * @return 集合
     */
    public static <Source, Target> List<Target> mapForList(Collection<Source> sourceList, Class<Target> targetClass) {
        return mapForList(sourceList, targetClass, null);
    }

    /**
     * 转换为列表
     * @param sourceList 源列表对象
     * @param targetClass 目标类型对象
     * @param convertAction 函数式编程-copy的属性名称不一样，可在这里单独设置
     * @return 集合
     */
    public static <Source, Target> List<Target> mapForList(Collection<Source> sourceList,
                                                           Class<Target> targetClass,
                                                           FunctionTwoAction<Source, Target> convertAction) {
        if (sourceList == null) {
            return new ArrayList<>(16);
        }
        List<Target> items = new ArrayList<Target>(sourceList.size());
        if (convertAction != null) {
            for (Source source : sourceList) {
                Target item = map(source, targetClass);
                convertAction.apply(source, item);
                items.add(item);
            }
        } else {
            for (Source source : sourceList) {
                Target item = map(source, targetClass);
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 转换
     * @param source 源对象
     * @param targetClass 目标类型对象
     * @return 目标对象
     */
    public static <Target> Target map(Object source, Class<Target> targetClass) {
        if (source == null) {
            return null;
        }
        return MODEL_MAPPER.map(source, targetClass);
    }

    /**
     * 转换
     * @param source 源对象
     * @param targetClass 目标类型对象
     * @param convertAction 函数式编程-copy的属性名称不一样，可在这里单独设置
     * @return 目标对象
     */
    public static <Target> Target map(Object source, Class<Target> targetClass, FunctionTwoAction<Object, Target> convertAction) {
        if (source == null) {
            return null;
        }
        Target result = MODEL_MAPPER.map(source, targetClass);
        if (convertAction != null) {
            convertAction.apply(source, result);
        }
        return result;
    }

    /**
     * 转换
     * @param source 源对象
     * @param targetClass 目标类型对象
     * @param typeMapName 列类型名称
     * @return 目标对象
     */
    public <Target> Target map(Object source, Class<Target> targetClass, String typeMapName) {
        if (source == null) {
            return null;
        }
        return MODEL_MAPPER.map(source, targetClass, typeMapName);
    }

    /**
     * 转换
     * @param source 源对象
     * @param targetClass 目标类型对象
     * @param typeMapName 列类型名称
     * @param convertAction 函数式编程-copy的属性名称不一样，可在这里单独设置
     * @return 目标对象
     */
    public <Target> Target map(Object source, Class<Target> targetClass, String typeMapName, FunctionTwoAction<Object, Target> convertAction) {
        if (source == null) {
            return null;
        }
        Target result = MODEL_MAPPER.map(source, targetClass, typeMapName);
        if (convertAction != null) {
            convertAction.apply(source, result);
        }
        return result;
    }

    /**
     * 添加转换
     * @param converter 转换器
     */
    public static <T, M> void addConverter(Converter<T, M> converter) {
        MODEL_MAPPER.addConverter(converter);
    }

    /**
     * 添加映射
     * @param propertyMap 属性列表
     * @return
     */
    public <T, M> TypeMap<T, M> addMappings(PropertyMap<T, M> propertyMap) {
        return MODEL_MAPPER.addMappings(propertyMap);
    }
}
