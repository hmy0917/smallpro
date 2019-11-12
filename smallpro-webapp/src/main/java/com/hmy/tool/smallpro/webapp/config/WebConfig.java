package com.hmy.tool.smallpro.webapp.config;

import org.springframework.aop.SpringProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.keep.commons.web.interceptor.AccessControlInterceptor;
import com.keep.commons.web.interceptor.I18nInterceptor;
import com.keep.commons.web.interceptor.RequestContextHolderInterceptor;
import com.keep.commons.web.interceptor.RequestLogInterceptor;
import com.keep.kit.smallpro.webapp.interceptor.Log4jContextHolderInterceptor;
import com.keep.kit.smallpro.webapp.interceptor.CommonInterceptor;
import com.keep.spring.boot.dubbo.DubboReference;
import com.keep.user.account.rpc.service.UserRpcService;

/**
 * Date: 2018/9/14
 * Time: 下午3:40
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements SpringProxy {

    @DubboReference(registry = "dubbo-default-registry", timeout = 1000, version = "1.0")
    private UserRpcService userRpcService;

    @Bean
    public CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor());
        registry.addInterceptor(new AccessControlInterceptor(userRpcService));
        registry.addInterceptor(new RequestContextHolderInterceptor());
        registry.addInterceptor(new I18nInterceptor());
        registry.addInterceptor(new Log4jContextHolderInterceptor());
        registry.addInterceptor(new RequestLogInterceptor());
    }
}
