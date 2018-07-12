/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午2:57
 */
package cn.com.geasy.marketing.web.system;

import com.gitee.mechanic.web.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@RestController
public class SysUserController {
    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUser(@PathVariable(required = true) Long id){
        return ResponseUtils.result("user");
    }
}
