/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午2:16
 */
package cn.com.geasy.marketing.api.system;

import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Login", description = "登录接口")
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthenticationManager authenticationManager;

    // 原请求信息的缓存及恢复
    private RequestCache requestCache = new HttpSessionRequestCache();

    // 用于重定向
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> login(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value="username",required=true) String username,
                                          @RequestParam(value="password",required=true) String password
    ) throws IOException {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            logger.debug("用户[" + username + "]已成功登录。");
            return ResponseUtils.result("用户[" + username + "]已成功登录。");
        } catch (AuthenticationException ex) {
            return ResponseUtils.result(ex.getMessage());
        }
    }

    @ApiOperation(value = "用户未登录时跳转到的接口")
    @GetMapping("/unauthor")
    public ResponseEntity<ModelMap> unauthor() {
        return ResponseUtils.result(HttpCode.UNAUTHORIZED);
    }

    @ApiOperation(value = "退出登录接口")
    @GetMapping("/logout")
    public ResponseEntity<ModelMap> logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);

        SecurityContextHolder.clearContext();
        return ResponseUtils.result("用户已退出登录。");
    }
}
