package cn.com.geasy.marketing.domain.dto.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class TagTreeDto {
    public TagTreeDto(Long parentId) {
        this.parentId = parentId;
    }

    public TagTreeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    private Long id;

    private String name;

    private List<TagTreeDto> subTag;//  = new ArrayList<>();
    @JsonIgnore
    private Long parentId;
}
