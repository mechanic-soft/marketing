package cn.com.geasy.marketing.domain.dto.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 标签Dto
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto implements Serializable {
    private static final long serialVersionUID = 5823500947847473318L;

    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名字
     */
    private String name;
    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 类型(基本属性/行为特征/兴趣爱好/个性标签)
     */
    private String typeName;


    /**
     * 子类型id
     */
    private Long subTypeId;

    /**
     * 子类型(性别/年龄)
     */
    private String subTypeName;


    /**
     *客户id
     */
    @JsonIgnore
    private Long customerId;

    /**
     * 标签来源(0=阅读,1=外呼，2=聊天)
     */
    private Integer tagSrc;
    /**
     * 是否系统标签(0=系统标签，1=自定义标签)
     */
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Integer isSys;



}
