package com.keep.kit.smallpro.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.keep.kit.smallpro.dao.ITestDao;
import com.keep.kit.smallpro.model.domain.mongo.TestDO;

/**
 * Date: 2018/9/14
 * Time: 下午5:51
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@Repository
public class TestDaoImpl implements ITestDao {

    @Autowired
    @Qualifier("mongo.kit")
    private MongoTemplate mongoTemplate;

    @Override
    public Object find(String str) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mac").is(str));
        return mongoTemplate.findOne(query, TestDO.class);
    }

}
