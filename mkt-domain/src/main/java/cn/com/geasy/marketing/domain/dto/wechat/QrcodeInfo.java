package cn.com.geasy.marketing.domain.dto.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Think on 2018/1/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeInfo {

    private String ticket;

    private String url;

    private Integer expireSeconds;
}
