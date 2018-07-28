/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 上午12:13
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SysUserService}
 *
 * @author phil
 * @version 1.0.0
 */
@DatabaseSetups(value = {
        @DatabaseSetup(value = "/dbunit/init/sys_user.setUp.xml")
})
public class SysUserServiceTest extends AbstractTransSpringBootDbunitTests {

    @Autowired
    private SysUserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindByUsername() throws Exception {
        SysUserDto expect = new SysUserDto();
        expect.setId(1L);
        expect.setUsername("admin");
        expect.setPassword("123456");
        expect.setRealName("name");
        expect.setWxUsername("wxusername");
        expect.setWxNickname("wxnickname");
        expect.setWxHeadImgUrl("WxHeadImgUrl");
        expect.setWxSex(1);
        expect.setWxSignature("signature");

        SysUserDto actual = this.userService.findByUsername("admin");

        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void testInsertOrUpdate() {
        SysUserDto insertData = new SysUserDto();
        insertData.setId(1L);
        insertData.setUsername("username");
        insertData.setPassword("123456");
        insertData.setRealName("realname");
        insertData.setWxUsername("wxusername");
        insertData.setWxNickname("wxnickname");
        insertData.setWxHeadImgUrl("wxheadimgirl");
        insertData.setWxSex(1);
        insertData.setWxSignature("wxsignature");

        this.userService.insertOrUpdate(insertData);
    }

    @Test
    public void testUsernameOrWxUinIsExist() {
        SysUserDto userDto = new SysUserDto();

        //用户名为admin。存在
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //用户名为notexist。不存在
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //用wxUin为333。不存在
        userDto.setUsername(null);
        userDto.setWxUin(333L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //用wxUin为1185887460。存在
        userDto.setWxUin(1185887460L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //username=admin, wxUin=333。存在
        userDto.setUsername("admin");
        userDto.setWxUin(333L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //username=notexist, wxUin=333。不存在
        userDto.setUsername("notexist");
        userDto.setWxUin(333L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //username=admin, wxUin=1185887460L。存在
        userDto.setUsername("admin");
        userDto.setWxUin(1185887460L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //username=notexist, wxUin=1185887460L。不存在
        userDto.setUsername("notexist");
        userDto.setWxUin(1185887460L);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();

        userDto = new SysUserDto();
        //id<>1,username=admin。不存在
        userDto.setId(1L);
        userDto.setWxUin(null);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=admin。存在
        userDto.setId(2L);
        userDto.setWxUin(null);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //id<>1,username=notexist。不存在
        userDto.setId(1L);
        userDto.setWxUin(null);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=notexist。不存在
        userDto.setId(2L);
        userDto.setWxUin(null);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();

        //id<>1,wxUin=333。不存在
        userDto.setId(1L);
        userDto.setWxUin(333L);
        userDto.setUsername(null);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,wxUin=333。不存在
        userDto.setId(2L);
        userDto.setWxUin(333L);
        userDto.setUsername(null);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();

        //id<>1,wxUin=1185887460。不存在
        userDto.setId(1L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername(null);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,wxUin=1185887460。存在
        userDto.setId(2L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername(null);
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();
        //id<>1,username=admin, wxUin=333。不存在
        userDto.setId(1L);
        userDto.setWxUin(333L);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=admin, wxUin=333。不存在
        userDto.setId(2L);
        userDto.setWxUin(333L);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();

        //id<>1,username=admin, wxUin=1185887460。不存在
        userDto.setId(1L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=admin, wxUin=1185887460。存在
        userDto.setId(2L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername("admin");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();

        //id<>1,username=notexist, wxUin=333。不存在
        userDto.setId(1L);
        userDto.setWxUin(333L);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=notexist, wxUin=333。不存在
        userDto.setId(2L);
        userDto.setWxUin(1333L);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();

        //id<>1,username=notexist, wxUin=1185887460。不存在
        userDto.setId(1L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isFalse();
        //id<>2,username=notexist, wxUin=1185887460。不存在
        userDto.setId(2L);
        userDto.setWxUin(1185887460L);
        userDto.setUsername("notexist");
        assertThat(this.userService.usernameOrWxUinIsExist(userDto)).isTrue();

    }
}
