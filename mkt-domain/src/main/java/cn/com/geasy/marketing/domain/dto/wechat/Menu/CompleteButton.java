package cn.com.geasy.marketing.domain.dto.wechat.Menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by fjh on 2017/7/22.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteButton extends Button {

    /**
     * 完整的菜单
     */
    @JsonProperty(value = "sub_button")
    private List<CommonButton> subButton;

}
