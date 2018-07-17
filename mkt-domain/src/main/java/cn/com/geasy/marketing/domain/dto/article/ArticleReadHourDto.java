package cn.com.geasy.marketing.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Think on 2018/1/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReadHourDto implements Serializable {

    private static final long serialVersionUID = 8516018907603017275L;
    /**
     * 小时
     */
    private Integer hours;

    /**
     * 对应小时阅读时间和
     */
    private Integer readTimeCount;
}
