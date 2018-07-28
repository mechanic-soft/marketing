/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:06
 */
package cn.com.geasy.marketing.domain.dto.task;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 待办任务Dto
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto implements Serializable {
    private static final long serialVersionUID = 5276473800609171477L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * 状态(0=删除,1=正常)
     */
    private Integer status;

    /**
     * 用户id
     */
    private List<Long> userId;

    /**
     * 创建人
     */
    private String createUserName ;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate createTime;
/*    *//**
     * 创建记录的用户id
     *//*
    private BigInteger createUserId;
    *//**
     * 创建记的时间
     *//*
    private LocalDateTime createTime;
    *//**
     * 记录最后一次更新的用户id
     *//*
    private BigInteger updateUserId;
    *//**
     * 更改记录的时间
     *//*
    private LocalDateTime updateTime;*/
    @JsonIgnore
    public String getCreateTimeStr(){
        return null!=createTime?createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null;
    }
}
