/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午2:16
 */
package cn.com.geasy.marketing.api.system;

import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.shiro.utils.LoginUtils;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Login", description = "登录接口")
@Slf4j
@RestController
public class LoginController {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "登录账户", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pass", value = "登录密码", required = true, dataType = "String", paramType = "query")
    })
    @ApiOperation(value = "用户登录")
    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> login(@RequestParam(required = true) String user,
                                          @RequestParam(required = true) String pass) {
        LoginUtils.login(user, pass);
        return ResponseUtils.result("用户[" + user + "]已成功登录。");
    }

    @ApiOperation(value = "用户登录状态")
    @GetMapping(value = "status")
    public ResponseEntity<ModelMap> isLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseUtils.result("用户[" + subject.getPrincipal() + "]已登录。");
        } else {
            return ResponseUtils.result(HttpCode.UNAUTHORIZED);
        }
    }

    @ApiOperation(value = "用户退出登录")
    @GetMapping(value = "logout")
    public ResponseEntity<ModelMap> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseUtils.result("用户已退出登录。");
    }

    @ApiOperation(value = "用户未登录时跳转到的接口")
    @GetMapping("unauthor")
    public ResponseEntity<ModelMap> unauthor() {
        return ResponseUtils.result(HttpCode.UNAUTHORIZED);
    }

    @ApiOperation(value = "用户没有权限时跳转到的接口")
    @GetMapping("forbidden")
    public ResponseEntity<ModelMap> forbidden() {
        return ResponseUtils.result(HttpCode.FORBIDDEN);
    }

    @ApiOperation(value = "用户未登录或退出时时跳转到的接口")
    @GetMapping("/")
    public ResponseEntity<ModelMap> index(HttpServletRequest request) {
        return ResponseUtils.result(HttpCode.UNAUTHORIZED);
    }
}
