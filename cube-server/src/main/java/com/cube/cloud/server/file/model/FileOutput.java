package com.cube.cloud.server.file.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * 文件输出参数
 * @author Long
 * @date 2023-01-10 15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = -898171288255283435L;

    @ApiModelProperty(value = "类型(0 : 图片、1 : 视频、2 : 其他、3 : APK、4 : 音频)")
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "访问路径")
    private String accessUrl;

    @ApiModelProperty(value = "备注")
    private String remark;
}
