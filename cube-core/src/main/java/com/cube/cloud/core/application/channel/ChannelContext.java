package com.cube.cloud.core.application.channel;

import java.util.Collection;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-09 12:03
 */
public interface ChannelContext<T extends Channel> {


    /**
     * 注册
     * @param channel 通道
     */
    void register(T channel);

    /**
     * 移除
     * @param channelId 通道id
     * @return boolean
     */
    boolean remove(String channelId);

    /**
     * 根据channelId获取通道
     * @param channelId 通道id
     * @return T
     */
    T getChannel(String channelId);

    /**
     * 通道是否存在
     * @param channelId 通道id
     * @return boolean
     */
    boolean exist(String channelId);

    /**
     * 清除所有
     */
    void clear();

    /**
     * 获取通道id集
     * @return Set<String>
     */
    Set<String> channelIdSet();

    /**
     * 获取通道集合
     * @return Collection<T>
     */
    Collection<T> getChannels();
}
