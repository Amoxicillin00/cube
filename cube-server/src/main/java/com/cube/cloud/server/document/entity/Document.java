package com.cube.cloud.server.document.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-03 17:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cube_document")
public class Document extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = 1285672820067011560L;

    /**
     * 文案标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文案内容
     */
    @TableField(value = "content")
    private String content;
}
