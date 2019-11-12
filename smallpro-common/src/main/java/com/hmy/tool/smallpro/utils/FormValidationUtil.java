package com.keep.kit.smallpro.utils;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Date: 2018/9/14
 * Time: 下午3:40
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
public class FormValidationUtil {

    public static String getErrorMessages(BindingResult bindingResult) {
        StringBuilder result = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<ObjectError> ls = bindingResult.getAllErrors();
            int size = ls.size();
            for (int i = 0; i < size; i++) {
                ObjectError error = ls.get(i);
                String field = ((FieldError) error).getField();
                result.append(field).append(": ").append(error.getDefaultMessage());
                if (i < size - 1) {
                    result.append("; ");
                }
            }
        }
        return result.toString();
    }

}
