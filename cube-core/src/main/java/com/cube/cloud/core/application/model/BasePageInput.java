package com.cube.cloud.core.application.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 15:27
 */
@Data
public class BasePageInput implements Serializable {

    private static final long serialVersionUID = -8232455552726555643L;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页，默认为第1页")
    private Integer current;

    /**
     * 每页显示条数，默认 10，最大 200
     */
    @ApiModelProperty("每页显示数，默认 10，最大 200")
    private Integer size;


    public int getCurrent() {
        if (current == null) {
            return 1;
        }
        return current;
    }

    public int getSize() {
        if (size == null) {
            return 10;
        }
        if (size > 200) {
            return 200;
        }
        return size;
    }

    public boolean isLegal(Object id) {
        Pattern compile = Pattern.compile("^[0-9]\\d*|0$");
        Matcher matcher = compile.matcher(String.valueOf(id));
        return matcher.matches();
    }
}
