/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:53
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import com.gitee.mechanic.mybatis.base.SuperMapper;

/**
 * 公司数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysCorpMapper extends SuperMapper<SysCorp> {
//    /**
//     * 返回匹配指定多个角色ID的权限名称
//     *
//     * @param rolesId 角色ID
//     * @return Set&lt;String&gt; 权限名称
//     */
    //Set<String> selectNamesByRolesId(@Param("rolesId") List<Long> rolesId);

    /**
     * 分页查询公司信息
     * @param sysCorpDto 查询条件
     * @param pagination 分页信息
     * @return List&lt;SysCorpDto&gt; 公司信息
     */
//    Page<SysCorp> selectAllPage(@Param("sc") SysCorpDto sysCorpDto, Pagination pagination);
}
