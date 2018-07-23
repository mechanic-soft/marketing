package cn.com.geasy.marketing.domain.entity.tag;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 标签类别
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tag_type")
public class TagType extends Entity implements Serializable {
    private static final long serialVersionUID = -8359493704342047868L;

    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别父ID
     */
    private Integer parentId;
}
