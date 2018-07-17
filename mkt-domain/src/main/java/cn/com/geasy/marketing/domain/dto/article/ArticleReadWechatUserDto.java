package cn.com.geasy.marketing.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Think on 2018/1/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReadWechatUserDto implements Serializable {

    private static final long serialVersionUID = -2371720616206085708L;
    private String nickName;

    private String headImgUrl;

    private Date createTime;

    private Integer readTime;
}
