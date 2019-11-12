package com.keep.kit.smallpro.webapp;

import static org.hamcrest.core.Is.is;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.keep.kit.smallpro.service.impl.TestServiceImpl;

/**
 * Date: 2018/11/22
 * Time: 下午4:16
 *
 * @author sunnyxd (fanxiaodong@keep.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
@TestPropertySource("classpath:test/application.properties")
@Ignore
public class BaseTest {

    @Autowired
    TestServiceImpl testService;

    @Test
    public void test() {
        String data = testService.getData("123");
        Assert.assertThat(data, is("123"));
    }

}
