package com.cube.cloud.core.application.channel;

import cn.hutool.core.util.StrUtil;
import com.cube.cloud.core.exception.ExceptionUtils;
import org.springframework.beans.factory.DisposableBean;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * 通道上下文抽象
 * @author Long
 * @date 2023-01-09 16:05
 */
public abstract class AbstractChannelContext<T extends Channel> implements ChannelContext<T>, DisposableBean {

    private final Map<String, T> channelMap;

    public AbstractChannelContext() {
        this(16);
    }

    public AbstractChannelContext(int total) {
        this.channelMap = new ConcurrentHashMap<>(total);
    }

    /**
     * 注册
     * @param channel 通道
     */
    @Override
    public void register(T channel) {
        this.channelMap.put(getKey(channel), channel);
    }

    /**
     * 移除
     * @param channelId 通道id
     * @return boolean
     */
    @Override
    public boolean remove(String channelId) {
        return this.channelMap.remove(this.getKey(channelId)) != null;
    }

    /**
     * 根据channelId获取通道
     * @param channelId 通道id
     * @return T
     */
    @Override
    public T getChannel(String channelId) {
        return this.channelMap.get(this.getKey(channelId));
    }

    /**
     * 通道是否存在
     * @param channelId 通道id
     * @return boolean
     */
    @Override
    public boolean exist(String channelId) {
        return this.channelMap.containsKey(this.getKey(channelId));
    }

    /**
     * 清楚所有
     */
    @Override
    public void clear() {
        this.channelMap.clear();
    }

    /**
     * 获取通道id集
     * @return Set<String>
     */
    @Override
    public Set<String> channelIdSet() {
        return this.channelMap.keySet();
    }

    /**
     * 获取通道集合
     * @return Collection<T>
     */
    @Override
    public Collection<T> getChannels() {
        return this.channelMap.values();
    }

    /**
     * 销毁
     * @throws Exception 异常信息
     */
    @Override
    public void destroy() throws Exception {
        this.channelMap.clear();
    }

    /**
     * 根据通道获取key值
     * @param channel 通道
     * @return String
     */
    private String getKey(T channel) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(channel.getChannelId()), "channelId不能为空");
        return channel.getChannelId().toUpperCase();
    }

    /**
     * 根据通道Id获取key值
     * @param channelId 通道Id
     * @return String
     */
    private String getKey(String channelId) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(channelId), "channelId不能为空");
        return channelId.toUpperCase();
    }
}
