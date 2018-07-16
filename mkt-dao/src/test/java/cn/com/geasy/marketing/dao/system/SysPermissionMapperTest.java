/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午9:39
 */
package cn.com.geasy.marketing.dao.system;

import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XmlDataSetLoader;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SysPermissionMapper}
 *
 * @author phil
 * @version 1.0.0
 */
@DbUnitConfiguration(dataSetLoader = XmlDataSetLoader.class)
@DatabaseSetups(value = {
        @DatabaseSetup(value = "/dbunit/init/sys_permission.xml")
})
public class SysPermissionMapperTest extends AbstractTransSpringBootDbunitTests {
    @Autowired
    private SysPermissionMapper permissionMapper;

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/init/rele_role_permission.xml")
    })
    @Test
    public void selectNamesByRolesId() {


        Set<String> permissionName = permissionMapper.selectNamesByRolesId(Lists.newArrayList(2L, 3L));
        assertThat(permissionName).isNotNull();
        assertThat(permissionName.size()).isEqualTo(2);
        assertThat(permissionName.contains("添加用户")).isTrue();
        assertThat(permissionName.contains("获取用户")).isTrue();

    }
}
