package cn.com.geasy.marketing.domain.dto.wechat.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by fjh on 2017/7/22.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonButton extends Button implements Serializable {

    private static final long serialVersionUID = -4262354544689281506L;
    /**
     * 菜单类型
     */
    private String type;

    /**
     * 链接
     */
    private String url;

    /**
     * 菜单key值
     */
    private String key;
}
