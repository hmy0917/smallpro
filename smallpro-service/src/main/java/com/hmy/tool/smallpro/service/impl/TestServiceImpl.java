package com.hmy.tool.smallpro.service.impl;

import com.keep.kit.smallpro.dao.ITestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date: 2018/9/14
 * Time: 下午5:51
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@Service
public class TestServiceImpl {

    @Autowired
    private ITestDao testDao;

    public String getData(String str) {
        Object o = testDao.find( str );
        if (o == null) {
            return str;
        }
        return o.toString();
    }

}
