package com.cube.cloud.server.file.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 文件输入参数
 * @author Long
 * @date 2023-01-10 15:59
 */
@Data
public class FileInput implements Serializable {

    private static final long serialVersionUID = -6170260102306728939L;

    @NotNull(message = "文件类型不能为空")
    @ApiModelProperty(value = "类型(0 : 图片、1 : 视频、2 : 其他、3 : APK、4 : 音频)")
    private Integer type;
}
