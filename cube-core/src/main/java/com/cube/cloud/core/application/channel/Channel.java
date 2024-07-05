package com.cube.cloud.core.application.channel;

/**
 * Created with IntelliJ IDEA.
 * 通道
 * @author Long
 * @date 2023-01-09 12:00
 */
public interface Channel {

    /**
     * 获取通道Id
     * @return String
     */
    String getChannelId();

    /**
     * 获取通道名称
     * @return String
     */
    String getChannelName();
}
