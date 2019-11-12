package com.keep.kit.smallpro.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 简单的对象数据转换器
 * 注：优先使用 Spring BeanUtils，不满足使用场景再用Converters
 * Date: 2018/9/13
 * Time: 下午12:09
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
public class Converters {

    public static <Input, Output> List<Output> convert(Collection<Input> inputs, Function<Input, Output> function) {
        List outputs;
        if (inputs == null || inputs.isEmpty()) {
            outputs = Collections.emptyList();
        } else {
            outputs = new ArrayList(inputs.size());
            for (Input input : inputs) {
                outputs.add(function.convert(input));
            }
        }
        return (List<Output>) outputs;
    }

    public static <Input, Output> Output convert(Input input, Function<Input, Output> function) {
        if (input == null) {
            return null;
        }
        return function.convert(input);
    }

}
