package com.cube.cloud.core.mybatisplus;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.web.context.BaseContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Mybatis-plus自动注入
 * @author Long
 * @date 2023-04-14 14:25
 */
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建人id字段
     */
    public static final String FIELD_CREATED_ID = "createdId";

    /**
     * 创建时间字段
     */
    public static final String FIELD_CREATED_TIME = "createdTime";

    /**
     * 修改人id字段
     */
    public static final String FIELD_MODIFIED_ID = "modifiedId";

    /**
     * 修改时间字段
     */
    public static final String FIELD_MODIFIED_TIME = "modifiedTime";




    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof AbstractModifyEntity) {
            if (BaseContext.isLogin()) {
                // 创建人id
                this.strictInsertFill(metaObject, FIELD_CREATED_ID, Long.class, StpUtil.getLoginIdAsLong());
                // 修改人id
                this.strictInsertFill(metaObject, FIELD_MODIFIED_ID, Long.class, StpUtil.getLoginIdAsLong());
            }
            // 创建时间
            this.strictInsertFill(metaObject, FIELD_CREATED_TIME, LocalDateTime::now, LocalDateTime.class);
            // 修改时间
            this.strictInsertFill(metaObject, FIELD_MODIFIED_TIME, LocalDateTime::now, LocalDateTime.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof AbstractModifyEntity) {
            if (BaseContext.isLogin()) {
                // 修改人id
                this.strictInsertFill(metaObject, FIELD_MODIFIED_ID, Long.class, StpUtil.getLoginIdAsLong());
            }
            // 修改时间
            this.strictInsertFill(metaObject, FIELD_MODIFIED_TIME, LocalDateTime::now, LocalDateTime.class);
        }
    }
}
