package com.keep.kit.smallpro.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keep.commons.web.model.response.ResponseEntity;

/**
 * Date: 2018/9/14
 * Time: 下午5:51
 *
 * @author sunnyxd (fanxiaodong@gotokeep.com)
 */
@RestController
public class MonitorController {

    @GetMapping("/health")
    public ResponseEntity index() {
        return new ResponseEntity("OK");
    }

}
