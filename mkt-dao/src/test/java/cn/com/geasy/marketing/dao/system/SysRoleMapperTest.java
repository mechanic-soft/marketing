/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午9:39
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.entity.system.SysRole;
import com.gitee.mechanic.test.AbstractTransSpringBootDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.XmlDataSetLoader;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SysRoleMapper}
 * @author phil
 * @version 1.0.0
 */
@DbUnitConfiguration(dataSetLoader = XmlDataSetLoader.class)
@DatabaseSetups(value = {
        @DatabaseSetup(value = "/dbunit/init/sys_role.xml")
})
@DirtiesContext
public class SysRoleMapperTest extends AbstractTransSpringBootDbunitTests {
    @Autowired
    private SysRoleMapper roleMapper;

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/init/rele_user_role.xml")
    })
    @Test
    public void testSelectByUserId(){
        List<SysRole> sysRoles = roleMapper.selectByUserId(2L);
        assertThat(sysRoles).isNotNull();
        assertThat(sysRoles.size()).isEqualTo(3);

        assertThat(sysRoles.get(0).getId()).isEqualTo(2);
        assertThat(sysRoles.get(0).getName()).isEqualTo("理财经理");
        assertThat(sysRoles.get(0).getDescription()).isEqualTo("理财经理");
        assertThat(sysRoles.get(0).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(1).getId()).isEqualTo(3);
        assertThat(sysRoles.get(1).getName()).isEqualTo("管理者");
        assertThat(sysRoles.get(1).getDescription()).isEqualTo("理财经理的上级管理人员");
        assertThat(sysRoles.get(1).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(2).getId()).isEqualTo(4);
        assertThat(sysRoles.get(2).getName()).isEqualTo("被删除的");
        assertThat(sysRoles.get(2).getDescription()).isEqualTo("被删除的");
        assertThat(sysRoles.get(2).getStatus()).isEqualTo(0);
    }

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/init/rele_role_permission.xml")
    })
    @Test
    public void testSelectByPermissionId(){
        List<SysRole> sysRoles = roleMapper.selectByPermissionId(1L);
        assertThat(sysRoles).isNotNull();
        assertThat(sysRoles.size()).isEqualTo(3);

        assertThat(sysRoles.get(0).getId()).isEqualTo(1);
        assertThat(sysRoles.get(0).getName()).isEqualTo("系统管理");
        assertThat(sysRoles.get(0).getDescription()).isEqualTo("拥有所有权限的系统管理员");
        assertThat(sysRoles.get(0).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(1).getId()).isEqualTo(2);
        assertThat(sysRoles.get(1).getName()).isEqualTo("理财经理");
        assertThat(sysRoles.get(1).getDescription()).isEqualTo("理财经理");
        assertThat(sysRoles.get(1).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(2).getId()).isEqualTo(3);
        assertThat(sysRoles.get(2).getName()).isEqualTo("管理者");
        assertThat(sysRoles.get(2).getDescription()).isEqualTo("理财经理的上级管理人员");
        assertThat(sysRoles.get(2).getStatus()).isEqualTo(1);
    }

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/init/rele_role_permission.xml")
    })
    @Test
    public void testSelectByPermissionsId(){
        List<Long> permissionsId = Lists.newArrayList(1L, 2L);

        List<SysRole> sysRoles = roleMapper.selectByPermissionsId(permissionsId);
        assertThat(sysRoles).isNotNull();
        assertThat(sysRoles.size()).isEqualTo(3);

        assertThat(sysRoles.get(0).getId()).isEqualTo(1);
        assertThat(sysRoles.get(0).getName()).isEqualTo("系统管理");
        assertThat(sysRoles.get(0).getDescription()).isEqualTo("拥有所有权限的系统管理员");
        assertThat(sysRoles.get(0).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(1).getId()).isEqualTo(2);
        assertThat(sysRoles.get(1).getName()).isEqualTo("理财经理");
        assertThat(sysRoles.get(1).getDescription()).isEqualTo("理财经理");
        assertThat(sysRoles.get(1).getStatus()).isEqualTo(1);

        assertThat(sysRoles.get(2).getId()).isEqualTo(3);
        assertThat(sysRoles.get(2).getName()).isEqualTo("管理者");
        assertThat(sysRoles.get(2).getDescription()).isEqualTo("理财经理的上级管理人员");
        assertThat(sysRoles.get(2).getStatus()).isEqualTo(1);
    }

    @ExpectedDatabase(value = "/dbunit/delete/sys_role.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testDelete(){
        this.roleMapper.deleteById(3L);
    }
}
