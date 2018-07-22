package cn.com.geasy.marketing.api.tag;

import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.service.tag.TagService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签 访问Controller
 *
 * @author yml
 * @version 1.0.0
 */
@Api(tags = "Tags", description = "标签管理接口")
@Slf4j
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "新增标签")
    @PostMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> addTag(@RequestBody Tag tag){
        return ResponseUtils.result(null);
    }
}
