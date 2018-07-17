package cn.com.geasy.marketing.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Think on 2018/1/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedTraceDto implements Serializable {

    private static final long serialVersionUID = -4752409587284978866L;
    private String nickName;

    private String headImgUrl;

    private String city;

    private Long id;

    private Long parentId;

    private Integer shareStatus;

    private Long userId;
}
