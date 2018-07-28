/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 上午12:13
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
//@PrepareForTest(PageUtils.class)
public class SysUserServiceTest extends AbstractTransSpringBootDbunitTests {

    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private SysUserService userService;

    private SysUserDto admin = new SysUserDto();

    @Before
    public void setUp() throws Exception {
        admin = new SysUserDto();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8");
        admin.setRealName("管理员");
        admin.setWxUin(1185887460L);
        admin.setWxUsername("@42dc1e0cb92c1eb6eda3cb65c0ddbf64");
        admin.setWxNickname("格物致知");
        admin.setWxHeadImgUrl("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c");
        admin.setWxSex(1);
        admin.setWxSignature("一代鲜肉替腊肉，终究风干无人识。");
    }

    @Test
    public void testFindDtosPage() throws Exception {
//
//        Page<Object> page = PageUtils.getPage(2,2);
//
//        mockStatic(PageUtils.class);
//        when(PageUtils.getPage(anyInt())).thenReturn(page);

        Page<SysUserDto> userDtoPage = this.userService.findDtos(1);
        assertThat(userDtoPage.getTotal()).isEqualTo(3);
        assertThat(userDtoPage.getCurrent()).isEqualTo(1);
        assertThat(userDtoPage.getRecords().size()).isEqualTo(3);
        assertThat(userDtoPage.getRecords().get(2).getId()).isEqualTo(3L);
        assertThat(userDtoPage.getRecords().get(2).getUsername()).isEqualTo("user");
    }

    @Test
    public void testFindDtos() throws Exception {
        List<SysUserDto> userDtos = this.userService.findDtos();
        assertThat(userDtos.size()).isEqualTo(3);
        assertThat(userDtos.get(0).getId()).isEqualTo(1L);
        assertThat(userDtos.get(0).getUsername()).isEqualTo("admin");
        assertThat(userDtos.get(2).getId()).isEqualTo(3L);
        assertThat(userDtos.get(2).getUsername()).isEqualTo("user");
    }

    @Test
    public void testFindDtoById() throws Exception {


        SysUserDto actual = this.userService.findDtoById(1L);
        assertThat(actual).isEqualTo(admin);
    }

    @Test
    public void testFindByUsername() throws Exception {
//        SysUserDto expect = new SysUserDto();
//        expect.setId(1L);
//        expect.setUsername("admin");
//        expect.setPassword("4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8");
//        expect.setRealName("管理员");
//        expect.setWxUin(1185887460L);
//        expect.setWxUsername("@42dc1e0cb92c1eb6eda3cb65c0ddbf64");
//        expect.setWxNickname("格物致知");
//        expect.setWxHeadImgUrl("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c");
//        expect.setWxSex(1);
//        expect.setWxSignature("一代鲜肉替腊肉，终究风干无人识。");

        SysUserDto actual = this.userService.findByUsername("admin");

        assertThat(actual).isEqualTo(admin);
    }

    @Test
    public void testFindByByWxUin() throws Exception {
//        SysUserDto expect = new SysUserDto();
//        expect.setId(1L);
//        expect.setUsername("admin");
//        expect.setPassword("4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8");
//        expect.setRealName("管理员");
//        expect.setWxUin(1185887460L);
//        expect.setWxUsername("@42dc1e0cb92c1eb6eda3cb65c0ddbf64");
//        expect.setWxNickname("格物致知");
//        expect.setWxHeadImgUrl("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c");
//        expect.setWxSex(1);
//        expect.setWxSignature("一代鲜肉替腊肉，终究风干无人识。");

        SysUserDto actual = this.userService.findByWxUin(1185887460L);

        assertThat(actual).isEqualTo(admin);
    }

    @ExpectedDatabase(value = "/dbunit/init/sys_user.delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testRemove() throws Exception {
        List<Long> ids = Lists.newArrayList(1L, 3L);
        this.userService.remove(ids);
    }

    @Test
    public void testUpdateByUsernameWhenWxUinExistShouldThrowServiceException() throws Exception {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("微信UIN[1185887460]已存在。");
        SysUserDto wxUniExistUser = new SysUserDto();
        wxUniExistUser.setWxUin(1185887460L);
        this.userService.updateByUsername(wxUniExistUser);
    }

    @ExpectedDatabase(value = "/dbunit/init/sys_user.update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testUpdateByUsername() throws Exception {
        SysUserDto wxUniExistUser = new SysUserDto();
        wxUniExistUser.setUsername("admin");
        wxUniExistUser.setWxUin(1185834434387460L);
        wxUniExistUser.setWxUsername("@42dc1e0cb9有意义2c1eb6eda3cb65c0ddbf64");
        wxUniExistUser.setWxNickname("格物致知122");
        wxUniExistUser.setWxHeadImgUrl("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1e");
        wxUniExistUser.setWxSex(2);
        wxUniExistUser.setWxSignature("一代鲜肉替腊肉，终究风干无人识333。");
        this.userService.updateByUsername(wxUniExistUser);
    }

    @Test
    public void testInsertOrUpdateWhenUsernameExistShouldThrowServiceException()  throws Exception {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("用户名或微信UIN已存在");
        SysUserDto insertData = new SysUserDto();
        insertData.setUsername("user");

        this.userService.insertOrUpdate(insertData);
    }

    @Test
    public void testInsertOrUpdateWhenWxUinExistShouldThrowServiceException()  throws Exception {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("用户名或微信UIN已存在");
        SysUserDto insertData = new SysUserDto();
        insertData.setWxUin(1185887460L);

        this.userService.insertOrUpdate(insertData);
    }

    @ExpectedDatabase(value = "/dbunit/init/sys_user.insert.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testInsertOrUpdate()  throws Exception {
        SysUserDto insertData = new SysUserDto();
//        insertData.setId(1L);
        insertData.setUsername("insertuser");
        insertData.setPassword("112");
        insertData.setRealName("333");
        insertData.setWxUin(444L);
        insertData.setWxUsername("dddd");
        insertData.setWxNickname("大师傅");
        insertData.setWxHeadImgUrl("eticon?seq=1856357848&username=@42dc1e0cb92c1e");
        insertData.setWxSex(1);
        insertData.setWxSignature("一多少啊识333。");

        this.userService.insertOrUpdate(insertData);
    }

    @Test
    public void testUsernameOrWxUinIsExist()  throws Exception {
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
