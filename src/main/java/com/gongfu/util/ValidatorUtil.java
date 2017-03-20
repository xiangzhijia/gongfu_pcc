package com.gongfu.util;

import com.gongfu.base.RestModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author summer
 * @ClassName:ValidatorUtil.java
 * @Description:validator的工具
 * @date 2016年3月31日
 */
public class ValidatorUtil {

    public static RestModel handleBingResult(BindingResult bindingResult) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append("<br>");
        }
        return RestModel.create().errorMsg(String.valueOf(sb));
    }
}
