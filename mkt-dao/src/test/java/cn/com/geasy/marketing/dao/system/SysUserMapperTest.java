/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午9:39
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XmlDataSetLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SysUserMapper}
 *
 * @author phil
 * @version 1.0.0
 */
@DbUnitConfiguration(dataSetLoader = XmlDataSetLoader.class)
@DatabaseSetups(value = {
        @DatabaseSetup(value = "/dbunit/init/sys_user.xml")
})
public class SysUserMapperTest extends AbstractTransSpringBootDbunitTests {
    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void testFindByUsername() {

        SysUserDto sysUser = userMapper.findByUsername("admin");

        assertThat(sysUser).isNotNull();

        assertThat(sysUser.getId()).isEqualTo(1);
        assertThat(sysUser.getUsername()).isEqualTo("admin");
        assertThat(sysUser.getPassword()).isEqualTo("4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8");
//        assertThat(sysUser.getSalt()).isEqualTo("e5a721750f676a48ee77f3fe8c5a58e9");
        assertThat(sysUser.getRealName()).isEqualTo("管理员");
        assertThat(sysUser.getWxUin()).isEqualTo(1185887460L);
        assertThat(sysUser.getWxUsername()).isEqualTo("@42dc1e0cb92c1eb6eda3cb65c0ddbf64");
        assertThat(sysUser.getWxHeadImgUrl()).isEqualTo("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c");
        assertThat(sysUser.getWxNickname()).isEqualTo("格物致知");
        assertThat(sysUser.getWxSex()).isEqualTo(1);
        assertThat(sysUser.getWxSignature()).isEqualTo("一代鲜肉替腊肉，终究风干无人识。");
    }

//    @Test
//    public void testSelectById(){
//        SysUser  sysUser = userMapper.findByUsername("admin");
//    }
}
