package cn.com.geasy.marketing.domain.dto.wechat.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 公众号菜单
 * Created by fjh on 2017/7/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    /**
     *菜单按钮
     */
    private List<Button> button;
}
