package cn.com.geasy.marketing.api.tag;

import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.domain.entity.tag.TagType;
import cn.com.geasy.marketing.service.tag.TagService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签 访问Controller
 *
 * @author yml
 * @version 1.0.0
 */
@Api(tags = "Tags", description = "标签管理接口")
@Slf4j
@RestController
@RequestMapping("/v1")
public class TagController {
    @Autowired
    private TagService tagService;


    @ApiOperation(value = "新增标签")
    @PostMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> addTag(@RequestBody Tag tag,@RequestParam Integer tagarentsTypeId){
        return ResponseUtils.result(tagService.addTag(tag,tagarentsTypeId));
    }

    @ApiOperation(value = "新增标签类别")
    @PostMapping(path = "/tagTypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> addTagType(@RequestBody TagType tagType){
        return ResponseUtils.result(tagService.addTagType(tagType));
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> removeTag(@RequestBody List<Long> ids){
        return ResponseUtils.result(tagService.removeTag(ids));
    }

    @ApiOperation(value = "修改标签")
    @PutMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> updateTag(@RequestBody Tag tag,@RequestParam Integer tagarentsTypeId){
        return ResponseUtils.result(tagService.updateTag(tag,tagarentsTypeId));
    }

    @ApiOperation(value = "标签列表")
    @GetMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> getTagsList(@RequestParam Integer pageNum){
        return ResponseUtils.result(tagService.findSystemTagList(pageNum));
    }

    @ApiOperation(value = "标签类别")
    @GetMapping(path = "/tagTyPes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getTagsTypeList(){
        return ResponseUtils.result(tagService.findTagType());
    }

}
