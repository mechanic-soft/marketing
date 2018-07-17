package cn.com.geasy.marketing.domain.dto.wechat.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by fjh on 2017/7/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Button implements Serializable{

    /**
     * 菜单名
     */
    private String name;
}
