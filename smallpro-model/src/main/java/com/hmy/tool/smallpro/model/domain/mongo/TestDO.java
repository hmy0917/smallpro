package com.hmy.tool.smallpro.model.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * test do
 * Date: 2018/9/14
 * Time: 下午5:51
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "testCollection")
public class TestDO {

    @Id
    @JsonProperty("_id")
    private String id;
    private String userId;
    private Long updatedAt;

}
