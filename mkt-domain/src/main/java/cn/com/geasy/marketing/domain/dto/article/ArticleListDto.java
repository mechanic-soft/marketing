package cn.com.geasy.marketing.domain.dto.article;

import cn.com.geasy.marketing.domain.entity.article.Article;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Think on 2018/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDto implements Serializable {

    private static final long serialVersionUID = -6466606175956312955L;
    private Article article;

    private Integer readCount;

    private Integer sharedCount;

    private List<Tag> tags;
}
