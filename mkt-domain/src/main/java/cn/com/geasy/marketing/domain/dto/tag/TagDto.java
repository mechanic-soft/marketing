package cn.com.geasy.marketing.domain.dto.tag;

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
     *
     */
    private Long id;

    private String typeName;

    private String subTypeName;

    private String name;

    private String tagSrc;

}
