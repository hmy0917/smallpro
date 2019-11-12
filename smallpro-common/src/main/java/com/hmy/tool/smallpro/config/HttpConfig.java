package com.keep.kit.smallpro.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import com.keep.commons.utils.http.OkHttpUtils;

/**
 * Date: 2018/11/9
 * Time: 下午3:45
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
public class HttpConfig {

    @Bean
    @Qualifier("internalHttpUtils")
    public OkHttpUtils internalHttpUtils() {
        return new OkHttpUtils(1000, 1000, 1000);
    }

}
