package cn.com.geasy.marketing.domain.dto.article;

import cn.com.geasy.marketing.domain.entity.article.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleHotDto extends Article {

    private Integer readCount;
}
