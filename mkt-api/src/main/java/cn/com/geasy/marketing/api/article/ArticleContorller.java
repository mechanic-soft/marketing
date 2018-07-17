/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:57
 */
package cn.com.geasy.marketing.api.article;

import cn.com.geasy.marketing.domain.dto.article.ArticleDto;
import cn.com.geasy.marketing.mapstruct.article.ArticleMapstruct;
import cn.com.geasy.marketing.service.article.ArticleService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章API
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Article", description = "文章接口")
@Slf4j
@RestController
public class ArticleContorller {

    private final ArticleService articleService;

    @Autowired
    public ArticleContorller(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation(value = "获取文章列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "tagIds", value = "标签ID", paramType = "body", dataType = "List<Long>")
    })
    @ApiResponse(code = 200, message = "message", responseContainer = "List")
    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> get(@RequestBody List<Long> tagIds){
        return ResponseUtils.result();
    }

    @ApiOperation(value = "获取文章")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "文章ID", paramType = "path", dataType = "Long")
    })
    @GetMapping(path = "/articles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> get(@PathVariable Long id){
        return ResponseUtils.result(ArticleMapstruct.getInstance.toDto(articleService.selectById(id)));
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids){
        return ResponseUtils.result();
    }

    @ApiOperation(value = "添加文章")
    @PostMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> add(@RequestParam Boolean isLink,
                                         @RequestBody ArticleDto article){
        if (isLink){
            if (StringUtils.isBlank(article.getOriginUrl())){
                return ResponseUtils.result("文章链接不能为空。");
            }
        } else {
            if (StringUtils.isBlank(article.getContent())){
                return ResponseUtils.result("文章内容不能为空");
            }
        }
        return ResponseUtils.result();
    }

    @ApiOperation(value = "编辑文章")
    @PutMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> edit(@RequestBody ArticleDto article){
        return ResponseUtils.result();
    }

//    @ApiOperation(value = "添加手工编辑文章")
//    @PostMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<ModelMap> save(@RequestBody List<Long> ids){
//        return ResponseUtils.result();
//    }



//    private final ArticleService articleService;
//
//
//    private final TagService tagService;
//
//    @Autowired
//    public ArticleContorller(ArticleService articleService, TagService tagService) {
//        this.articleService = articleService;
//        this.tagService = tagService;
//    }
//
//    /**
//     * 微信文章生成
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    @ApiOperation(value = "微信文章生成")
//    @ResponseBody
//    public ResponseEntity<ModelMap> saveArticle(@RequestBody Article article) {
//        article = articleService.saveArticle(article, SessionUtils.getCurrentMpUserId());
//        return ResponseUtils.result(article);
//    }
//
//    /**
//     * 微信文章
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/get/article", method = RequestMethod.GET)
//    @ApiOperation(value = "微信文章")
//    @ResponseBody
//    public ResponseEntity<ModelMap> getArticleById(@ApiParam(value = "文章id", required = true)
//                                                   @RequestParam(required = true) Long id) {
//
//        ArticleDto article = ArticleMapstruct.getInstance.toDto(articleService.selectById(id));
//        List<Tag> tagList = tagService.findTagByArticleId(id);
//        Map<String, Object> map = new HashMap<>();
//        map.put("article", article);
//        map.put("tags", tagList);
//        return ResponseUtils.result(map);
//    }
//
//    /**
//     * 文章列表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ApiOperation(value = "我的文章列表")
//    @ResponseBody
//    public ResponseEntity<ModelMap> findArticleList(Page page, String title) {
//        PageInfo<ArticleListDto> pageInfo = articleService.findByUserIdPage(page, title, SessionUtil.getCurrentWechatUserId());
//        return ResponseUtils.result(pageInfo);
//    }
//
//    /**
//     * 删除文章
//     *
//     * @param ids
//     * @return
//     */
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    @ApiOperation(value = "删除文章")
//    @ResponseBody
//    public ResponseEntity<ModelMap> deleteArticle(@ApiParam(value = "文章id", required = true)
//                                                  @RequestParam(required = true) Long[] ids) {
//        List<Long> idList = Arrays.asList(ids);
//        articleService.logicDelete(idList);
//        return JsonResponse.newOk();
//    }
//
//    /**
//     * 微信文章原图片url，换成图片流
//     *
//     * @param url
//     * @return
//     */
//    @RequestMapping(value = "/change/image", method = RequestMethod.GET)
//    @ApiOperation(value = "微信文章原图片url，换成图片流")
//    public void getImg(@ApiParam(value = "微信文章原图片url，换成图片流", required = true)
//                       @RequestParam(required = true) String url, HttpServletResponse response) {
//        HttpResponse httpResponse = HttpRequest.get(url).send();
//        response.setContentType("image/png");
//        OutputStream os = null;
//        try {
//            os = response.getOutputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            os.write(httpResponse.bodyBytes());
//            os.flush();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 微信文章
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/get/html", method = RequestMethod.GET)
//    @ApiOperation(value = "微信文章")
//    public Object getArticleHtmlById(@ApiParam(value = "文章id", required = true)
//                                     @RequestParam(required = true) Long id) {
//        return articleService.findById(id) == null ? "" : articleService.findById(id).getUrl();
//    }
//
//    @RequestMapping(value = "/hotList", method = RequestMethod.GET)
//    @ApiOperation("热文列表")
//    @ResponseBody
//    public ResponseEntity<ModelMap> findByHotArticlePage(Page page) {
//        PageInfo<ArticleHotDto> pageInfo = articleService.findByHotArticlePage(page);
//        return JsonResponse.newOk(pageInfo);
//    }
//
//    @RequestMapping(value = "/subscribeList", method = RequestMethod.GET)
//    @ApiOperation("订阅文列表")
//    @ResponseBody
//    public ResponseEntity<ModelMap> findBySubscribePage(Page page) {
//        PageInfo<ArticleListDto> pageInfo = articleService.findBySubscribePage(page);
//        return JsonResponse.newOk(pageInfo);
//    }
//
//
//    /**
//     * 微信文章原图片url，换成图片
//     *
//     * @param url
//     * @return
//     */
//    @RequestMapping(value = "/get/image", method = RequestMethod.GET)
//    @ApiOperation(value = "微信文章原图片url，换成图片")
//    public ResponseEntity<ModelMap> getImg(@ApiParam(value = "微信文章原图片url，换成图片流", required = true)
//                                           @RequestParam(required = true) String url) {
//        String imageUrl = articleService.getSpiderImageUrl(url);
//        return JsonResponse.newOk(imageUrl);
//    }
//
//
//    @RequestMapping(value = "/readRecord", method = RequestMethod.GET)
//    @ApiOperation("阅读记录轨迹列表")
//    @ResponseBody
//    public ResponseEntity<ModelMap> findRecentReadRecord(Page page, Long userId) {
//        PageInfo<ArticleReadRecordDto> pageInfo = articleService.findRecentReadRecord(page, userId);
//        return JsonResponse.newOk(pageInfo);
//    }
}
