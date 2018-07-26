/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午2:16
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.contant.PasswordConstant;
import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.service.security.CurrentUser;
import cn.com.geasy.marketing.service.system.SysUserService;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.web.exception.LoginException;
import com.gitee.mechanic.web.utils.RequestUtils;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    private final SysUserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, SysUserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> login(HttpServletRequest request,
                                          @RequestBody SysUserDto user
    ) throws IOException {

        String username = user.getUsername();
        String password = user.getPassword();

        try {
            this.authenticate(username, password);
            this.userService.updateByUsername(user);

            HttpSession session = request.getSession();
            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication = securityContext.getAuthentication();
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            currentUser.setWxUin(user.getWxUin());
            currentUser.setWxUsername(user.getWxUsername());
            currentUser.setWxNickname(user.getWxNickname());
            currentUser.setWxHeadImgUrl(user.getWxHeadImgUrl());
            currentUser.setWxSex(user.getWxSex());
            currentUser.setWxSignature(user.getWxSignature());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(currentUser, authentication.getCredentials(), authentication.getAuthorities());
            securityContext.setAuthentication(token);
        } catch (LoginException e) {
            this.logout();
            return ResponseUtils.result(HttpCode.ACCOUNT_PASSWORD_ERROR);
        }
        return ResponseUtils.result("用户[" + username + "]已成功登录。");

    }

    @ApiOperation(value = "微信UIN登录")
    @PostMapping(value = "/wx/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> login(HttpServletRequest request, @RequestParam(value = "uin", required = true) Long wxUin) {
        SysUserDto userDto = this.userService.findByWxUin(wxUin);
        if (userDto == null) {
            return ResponseUtils.result(HttpCode.WX_UIN_NOT_EXIST, "微信UIN不存在。");
        }
        try {
            this.authenticate(userDto.getUsername(), PasswordConstant.DEFAULT_PASSWORD);
        } catch (LoginException e) {
            return ResponseUtils.result(HttpCode.ACCOUNT_PASSWORD_ERROR);
        }
        return ResponseUtils.result("用户[" + userDto.getUsername() + "]已成功登录。");
    }

    @ApiOperation(value = "用户未登录时跳转到的接口")
    @GetMapping("/unauthor")
    public ResponseEntity<ModelMap> unauthor() {
        return ResponseUtils.result(HttpCode.UNAUTHORIZED);
    }

    @ApiOperation(value = "退出登录接口")
    @GetMapping("/logout")
    public ResponseEntity<ModelMap> logout(HttpServletRequest request, HttpServletResponse response) {
        logout();
        return ResponseUtils.result("用户已退出登录。");
    }

    private void authenticate(String username, String password) throws LoginException {
        try {
            HttpServletRequest request = RequestUtils.getRequest();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            logger.debug("用户[" + username + "]已成功登录。");
        } catch (AuthenticationException ex) {
            throw new LoginException(HttpCode.ACCOUNT_PASSWORD_ERROR);
        }

    }

    private void logout() {
        HttpServletRequest request = RequestUtils.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

}
