/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午9:39
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XmlDataSetLoader;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
    public void testSelectNamesByRolesId() {


        Set<String> permissionName = this.permissionMapper.selectNamesByRolesId(Lists.newArrayList(2L, 3L));
        assertThat(permissionName).isNotNull();
        assertThat(permissionName.size()).isEqualTo(2);
        assertThat(permissionName.contains("add_user")).isTrue();
        assertThat(permissionName.contains("get_user")).isTrue();

    }

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/init/sys_role.xml"),
            @DatabaseSetup(value = "/dbunit/init/rele_role_permission.xml")
    })
    @Test
    public void testSelectAllCaseRole() {

        SysRole role1 = new SysRole();
        role1.setId(1L);
        role1.setName("系统管理");
        role1.setDescription("拥有所有权限的系统管理员");

        SysRole role2 = new SysRole();
        role2.setId(2L);
        role2.setName("理财经理");
        role2.setDescription("理财经理");

        SysRole role3 = new SysRole();
        role3.setId(3L);
        role3.setName("管理者");
        role3.setDescription("理财经理的上级管理人员");

        List<SysPermission> permissions = this.permissionMapper.selectAllCaseRole();
        assertThat(permissions.size()).isEqualTo(4);
        assertThat(permissions.get(0).getId()).isEqualTo(1L);
        assertThat(permissions.get(0).getName()).isEqualTo("add_user");
        assertThat(permissions.get(0).getRoles().size()).isEqualTo(2);
        assertThat(permissions.get(0).getRoles()).contains(role1, role2);

        assertThat(permissions.get(1).getId()).isEqualTo(2L);
        assertThat(permissions.get(1).getName()).isEqualTo("update_user");
        assertThat(permissions.get(1).getRoles().size()).isEqualTo(1);
        assertThat(permissions.get(1).getRoles()).contains(role1);

        assertThat(permissions.get(2).getId()).isEqualTo(3L);
        assertThat(permissions.get(2).getName()).isEqualTo("delete_user");
        assertThat(permissions.get(2).getRoles().size()).isEqualTo(1);
        assertThat(permissions.get(2).getRoles()).contains(role1);

        assertThat(permissions.get(3).getId()).isEqualTo(4L);
        assertThat(permissions.get(3).getName()).isEqualTo("get_user");
        assertThat(permissions.get(3).getRoles().size()).isEqualTo(3);
        assertThat(permissions.get(3).getRoles()).contains(role1, role2, role3);
    }
}
