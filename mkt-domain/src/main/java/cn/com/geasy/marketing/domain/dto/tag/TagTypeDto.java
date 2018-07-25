package cn.com.geasy.marketing.domain.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagTypeDto implements Serializable {
    /**
     * 标签名称
     */
    private String name;
    /**
     * 类别父ID
     */
    private Integer parentId;
}
