package com.cube.cloud.server.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cube.cloud.core.application.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * 文件信息
 * @author Long
 * @date 2023-01-10 15:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cube_file")
public class File extends AbstractEntity<Long> {

    private static final long serialVersionUID = -195935982323035375L;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类型(0 : 图片、1 : 视频、2 : 其他、3 : APK、4 : 音频)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     *拓展名
     */
    @TableField(value = "extension_name")
    private String extensionName;

    /**
     * 大小
     */
    @TableField(value = "size")
    private String size;

    /**
     * 相对路径
     */
    @TableField(value = "absolute_path")
    private String absolutePath;

    /**
     * 访问路径
     */
    @TableField(value = "access_url")
    private String accessUrl;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @TableField(value = "modified_time")
    private LocalDateTime modifiedTime;
}
