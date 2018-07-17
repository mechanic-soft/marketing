package cn.com.geasy.marketing.domain.dto.article;

import cn.com.geasy.marketing.domain.entity.article.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Think on 2018/2/27.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReadRecordDto extends Article implements Serializable {

    private static final long serialVersionUID = -117834812392150244L;

    private  Integer readTime;
}
