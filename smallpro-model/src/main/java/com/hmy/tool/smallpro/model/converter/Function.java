package com.keep.kit.smallpro.model.converter;

/**
 * Date: 2018/9/14
 * Time: 下午12:09
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
public interface Function<Input, Output> {

    /**
     * convert Input to Output
     * 
     * @param input
     * @return
     */
    Output convert(Input input);

}
