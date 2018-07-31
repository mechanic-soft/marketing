/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/30 下午9:58
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.test.AbstractTransSpringPowerMockDbunitTests;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SysRoleService}
 *
 * @author phil
 * @version 1.0.0
 */
@DatabaseSetups(value = {
        @DatabaseSetup(value = "/dbunit/sys_role.setUp.xml"),
        @DatabaseSetup(value = "/dbunit/rele_user_role.xml"),
        @DatabaseSetup(value = "/dbunit/rele_role_permission.xml")
})
public class SysRoleServiceTest extends AbstractTransSpringPowerMockDbunitTests {

    @Autowired
    SysRoleService roleService;

    @ExpectedDatabases(value = {
            @ExpectedDatabase(value = "/dbunit/sys_role.delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT),
            @ExpectedDatabase(value = "/dbunit/rele_user_role.delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT),
            @ExpectedDatabase(value = "/dbunit/rele_role_permission.delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT),
    })
    @Test
    public void testRemove() throws Exception {
        this.roleService.remove(Lists.newArrayList(2L, 3L));
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testFindPage() throws Exception {
        Page<SysRoleDto> userDtoPage = this.roleService.findPage(1);
        assertThat(userDtoPage.getTotal()).isEqualTo(3);
        assertThat(userDtoPage.getPages()).isEqualTo(1);
        assertThat(userDtoPage.getCurrent()).isEqualTo(1);
        assertThat(userDtoPage.getRecords().size()).isEqualTo(3);

        assertThat(userDtoPage.getRecords().get(0).getPermissions().size()).isEqualTo(4);
        assertThat(userDtoPage.getRecords().get(1).getPermissions().size()).isEqualTo(2);
        assertThat(userDtoPage.getRecords().get(2).getPermissions().size()).isEqualTo(1);
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testFindAll() throws Exception {
        List<SysRoleDto> roles = this.roleService.findAll();
        assertThat(roles.size()).isEqualTo(3);
        assertThat(roles.get(0).getPermissions().size()).isEqualTo(4);
        assertThat(roles.get(1).getPermissions().size()).isEqualTo(2);
        assertThat(roles.get(2).getPermissions().size()).isEqualTo(1);
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testFindById() throws Exception {
        SysRoleDto roleDto = this.roleService.findById(1L);
        assertThat(roleDto.getName()).isEqualTo("系统管理");

        assertThat(roleDto.getPermissions().size()).isEqualTo(4L);
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testFindByUserId() throws Exception {
        List<SysRoleDto> roleDto = this.roleService.findByUserId(3L);
        assertThat(roleDto.get(0).getName()).isEqualTo("理财经理");

        assertThat(roleDto.get(0).getPermissions().size()).isEqualTo(1L);
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testInsert() throws Exception {
        SysPermissionDto permission = new SysPermissionDto();
        permission.setId(2L);

        SysRoleDto roleDto = new SysRoleDto();
        roleDto.setName("修改的名称");
        roleDto.setDescription("修改的描述");
        roleDto.setPermissions(Lists.newArrayList(permission));
        Long id = this.roleService.save(roleDto);
        assertThat(id).isNotNull();

        SysRoleDto saved = this.roleService.findById(id);
        assertThat(saved.getName()).isEqualTo(roleDto.getName());
        assertThat(saved.getDescription()).isEqualTo(roleDto.getDescription());
        assertThat(saved.getPermissions().size()).isEqualTo(1);
        assertThat(saved.getPermissions().get(0).getId()).isEqualTo(2L);
        assertThat(saved.getPermissions().get(0).getName()).isEqualTo("update_user");
    }

    @DatabaseSetup(value = "/dbunit/sys_permission.xml")
    @Test
    public void testUpdate() throws Exception {
        SysPermissionDto permission = new SysPermissionDto();
        permission.setId(2L);

        SysRoleDto roleDto = new SysRoleDto();
        roleDto.setId(1L);
        roleDto.setName("修改的名称");
        roleDto.setDescription("修改的描述");
        roleDto.setPermissions(Lists.newArrayList(permission));
        Long id = this.roleService.save(roleDto);
        assertThat(id).isNotNull();

        SysRoleDto saved = this.roleService.findById(roleDto.getId());
        assertThat(saved.getName()).isEqualTo("修改的名称");
        assertThat(saved.getDescription()).isEqualTo("修改的描述");
        assertThat(saved.getPermissions().size()).isEqualTo(1);
        assertThat(saved.getPermissions().get(0).getId()).isEqualTo(2L);
    }
}
