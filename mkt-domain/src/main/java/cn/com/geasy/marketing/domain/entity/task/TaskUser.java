/*
 * Copyright (c) 2004-2015 by KYD All rights reserved
 */
package cn.com.geasy.marketing.domain.entity.task;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 待办任务用户关联实体
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("rele_user_task")
public class TaskUser extends Entity implements Serializable {

    private static final long serialVersionUID = 870368668999098457L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 任务所属的id
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 任务所属的人的id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 状态(0=删除,1=正常)
     */
    private Integer status;

    /**
     * 创建记的时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    /**
     * 更改记录的时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

}