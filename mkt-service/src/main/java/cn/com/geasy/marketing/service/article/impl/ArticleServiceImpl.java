/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午8:02
 */
package cn.com.geasy.marketing.service.article.impl;

import cn.com.geasy.marketing.dao.article.ArticleMapper;
import cn.com.geasy.marketing.domain.dto.article.ArticleDto;
import cn.com.geasy.marketing.domain.entity.article.Article;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.service.article.ArticleService;
import cn.com.geasy.marketing.service.tag.TagService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class ArticleServiceImpl extends SuperServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final TagService tagService;
    private final WxContactService wxContactService;

    @Autowired
    public ArticleServiceImpl(TagService tagService, WxContactService wxContactService) {
        this.tagService = tagService;
        this.wxContactService = wxContactService;
    }

    @Override
    public Page<ArticleDto> findById(Long id) {
        Article article = super.selectById(id);
        //查标签
        List<Tag> tags = tagService.findTagByArticleId(id);
        //查订阅号

        //查微信用户
//        this.wxContactService
        return null;
    }

//    private final String PREFIX_WEIXIN_ARTICLE = "mp.weixin.qq.com";
//
//    private static String WEN_ZHANG_LAYOUT = "<!DOCTYPE html>\n" +
//            "<html>\n" +
//            "\n" +
//            "<head>\n" +
//            "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
//            "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1,maximum-scale=1, user-scalable=0\" />\n" +
//            "\t<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\n" +
//            "\t<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" />\n" +
//            "\t<meta name=\"format-detection\" content=\"telephone=no\" />\n" +
//            "\t<title>\n" +
//            "\t\t财经早知道\n" +
//            "\t</title>\n" +
//            "\t<script type=\"text/javascript\"></script>\n" +
//            "\n" +
//            "\t<link href=\"http://www.betawm.com/Beta.HomeMWeb/Finance/css/Finance.css?_btrs=bt-rs%3aBeta.HomeMWeb%2fFinance%2fcss%2ffinanceE.css%3f%3ft%3d1517815983%26ex%3d1%26fc%3d1\"\n" +
//            "\t  rel=\"stylesheet\" />\n" +
//            "\t<style>\n" +
//            "\t\t.beta_layer_msg_box .layer_msg_panel {\n" +
//            "\t\t\tpadding: 16px 0px 10px;\n" +
//            "\t\t}\n" +
//            "\t</style>\n" +
//            "</head>\n" +
//            "\n" +
//            "<body>\n" +
//            "\t<div id=\"FContent\">\n" +
//            "\t\t wenzhang\t\n" +
//            "\t</div>\n" +
//            "</body>\n" +
//            "\n" +
//            "</html>";
//
//    private final MultilpartPath multilpartPath;
//    private final DomainUrl domainUrl;
//    private final ArticleSharedService articleSharedService;
//    private final MessageTemplateService messageTemplateService;
//    private final ArticleMapper articleMapper;
//
//
//    @Autowired
//    public ArticleServiceImpl(MultilpartPath multilpartPath, DomainUrl domainUrl, ArticleSharedService articleSharedInfo, ArticleSharedService articleSharedService, MessageTemplateService messageTemplateService, ArticleMapper articleMapper) {
//        this.multilpartPath = multilpartPath;
//        this.domainUrl = domainUrl;
//        this.articleSharedService = articleSharedService;
//        this.messageTemplateService = messageTemplateService;
//        this.articleMapper = articleMapper;
//    }
//
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
//    public Article saveArticle(Article article, Long wechatUserId) {
//
//        if (article == null) {
//            return null;
//        }
//        if (article.getId() != null) {
//            return this.updatearticle(article);
//        }
//
//        return insertArticle(article, wechatUserId);
//    }
//
//    @Override
//    public Page<ArticleListDto> findByUserIdPage(Page page, String title, Long currentMpUserId) {
//
//        EntityWrapper<Article> ew = new EntityWrapper<>();
//        ew.eq("user_id", currentMpUserId);
//
//
//        PageHelper.startPage(page.getCurrentPage(), page.getSize(), true);
//        List<Article> list = articleMapper.findByUserIdPage(title, currentMpUserId);
//        PageInfo<Article> pageInfoArticle = new PageInfo<>(list);
//        List<ArticleListDto> articleListDtoList = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(list)) {
//            for (Article articleInfo : list) {
//                ArticleListDto articleListDto = new ArticleListDto();
//                articleListDto.setArticle(articleInfo);
//                Integer count = articleReadInfoMapper.selectsUserTotalByArticleId(articleInfo.getId());
//                Integer sharedCount=articleSharedInfoMapper.selectsUserTotalByArticleIdAndUserId(articleInfo.getId());
//                articleListDto.setSharedCount(sharedCount);
//                articleListDto.setReadCount(count);
//                articleListDtoList.add(articleListDto);
//            }
//        }
//
//        PageInfo<ArticleListDto> pageInfo = new PageInfo<>(articleListDtoList);
//        pageInfo.setTotal(pageInfoArticle.getTotal());
//        pageInfo.setFirstPage(pageInfoArticle.isFirstPage());
//        pageInfo.setLastPage(pageInfoArticle.isLastPage());
//        pageInfo.setPages(pageInfoArticle.getPages());
//        pageInfo.setPageNum(pageInfoArticle.getPageNum());
//        return pageInfo;
//    }
//
//    /**
//     * 修改文章
//     *
//     * @param article
//     * @return
//     */
//    private Article updatearticle(Article article) {
//        if (article.getContent().length() > 500) {
//            String url = FileUploadUtils.saveText(article.getContent(), multilpartPath.getHtmlPath(), domainUrl.getDomain());
//            article.setTargetUrl(url);
//            article.setContent("");
//        } else {
//            article.setTargetUrl("");
//        }
//        super.update(article, null);
//        return article;
//    }
//
//    /**
//     * 插入文章
//     *
//     * @param article
//     * @return
//     */
//    private Article insertArticle(Article article, Long currentWechatUserId) {
//        article.setUserId(currentWechatUserId);
//        if (StringUtils.isNotEmpty(article.getOriginUrl()) && article.getOriginUrl().contains(PREFIX_WEIXIN_ARTICLE)) {
//            //保存微信文章
//            article = saveWeiXinArticle(article.getOriginUrl(), article.getTitle(), article.getDesc(), currentWechatUserId);
//        } else if (StringUtils.isNotEmpty(article.getOriginUrl())) {
//            //其他文章
//            article = savePageArticle(article.getOriginUrl(), currentWechatUserId);
//        } else {
//            article = saveDefinedArticle(article);
//        }
//        /**
//         * 分享记录
//         */
//        ArticleShared articleShared = new ArticleShared();
//        articleShared.setParentId(0l);
//        articleShared.setArticleId(article.getId());
//        articleShared.setUserId(currentWechatUserId);
//        articleShared.setShareStatus(0);
//        /**
//         * 模板消息消息通知
//         */
//        articleSharedService.insert(articleShared);
//        messageTemplateService.sendCreateArticleMassage(article, articleShared.getId());
//        return article;
//    }
//
//    /**
//     * 保存微信文章
//     *
//     * @param url
//     * @return
//     */
//    public Article saveWeiXinArticle(String url, String title, String desc, Long currentWechatUserId) {
//
//        String text = SpiderUtils.getSpiderText(url);
//        String msg_cdn_url = RegExpUtils.getKeyValueBykey("msg_cdn_url", text);
//        msg_cdn_url = getSpiderImageUrl(msg_cdn_url);
//        String msg_title = RegExpUtils.getKeyValueBykey("msg_title", text);
//        text = RegExpUtils.mpArticleChangeImges(text);
//        String fileUrl = null;
//        fileUrl = FileUploadUtils.saveText(text, multilpartPath.getHtmlPath(), domainUrl.getDomain());
//        msg_title = StringUtils.isEmpty(title) ? msg_title : title;
//        Article article = new Article();
//        article.setOriginUrl(url);
//        article.setTargetUrl(fileUrl);
//        article.setTitle(msg_title);
//        article.setIcon(msg_cdn_url);
//        article.setDesc(desc);
//        article.setUserId(currentWechatUserId);
//        article.setId(IdGenerator.getLongId());
//        super.insert(article);
//        return article;
//    }
//
//    /**
//     * 爬虫url的图片
//     *
//     * @param imgUrl
//     * @return
//     */
//    public String getSpiderImageUrl(String imgUrl) {
//        return FileUploadUtils.getSpiderImageUrl(imgUrl, multilpartPath.getImagePath(), domainUrl.getDomain());
//    }
//
//    /**
//     * 保存一般文章
//     *
//     * @param url
//     * @return
//     */
//    public Article savePageArticle(String url, Long currentWechatUserId) {
//        String text = SpiderUtils.getSpiderText(url);
//        String fileUrl = "";
//
//        String msg_title = RegExpUtils.getTagBykey("title", text);
//        String msg_cdn_url = RegExpUtils.getIcon(text);
//        String desc = "";
//
//        if (url.contains("http://www.betawm.com")) {
//            fileUrl = saveCaijing(text);
//            msg_title = getRegText(text, "ShareTitle");
//            msg_cdn_url = getRegText(text, "ShareLogo");
//            desc = getRegText(text, "ShareDesc");
//        }
//
//        Article article = new Article();
//        article.setOriginUrl(url);
//        article.setTargetUrl(fileUrl);
//        article.setTitle(msg_title);
//        article.setIcon(msg_cdn_url);
//        article.setDesc(desc);
//        article.setUserId(currentWechatUserId);
//        article.setId(IdGenerator.getLongId());
//        super.insert(article);
//        return article;
//    }
//
//    private String saveCaijing(String text) {
//        String repjs = "http://as.betawm.com/Beta.HomeWeb/Attach/js([\\s\\S]*?).js";
//        String delReg = "<div v-if=\"!IsShare\" class=\"btn_list\" >";
//        String repReg = "<div v-if=\"!IsShare\" class=\"btn_list\"  style=\"display:none;\" >";
//        String url = RegExpUtils.find(text, repjs);
//        String wenzhang = SpiderUtils.getSpiderText(url);
//        wenzhang = RegExpUtils.replace(wenzhang, "imgs([\\s\\S]*?)/>", " http://www.betawm.com/Beta.HomeMWeb/Finance/");
//        wenzhang = wenzhang.replace(delReg, repReg);
//        String reg = "='<([\\s\\S]*?)>';";
//        String saveText = RegExpUtils.find(wenzhang, reg);
//        saveText = saveText.replace("='", "").replace("';", "");
//        saveText = WEN_ZHANG_LAYOUT.replace("wenzhang", saveText);
//        String fileUrl = FileUploadUtils.saveText(saveText, multilpartPath.getHtmlPath(), domainUrl.getDomain());
//        return fileUrl;
//    }
//
//    public String getRegText(String text, String reg) {
//        text = RegExpUtils.find(text, reg + " = '([\\s\\S]*?)';");
//        text = text.replace(reg, "").replace(" ", "").replace("=", "")
//                .replace("'", "").replace(";", "").replace("'", "");
//        return text;
//    }
//
//    /**
//     * 保存自定义文章
//     *
//     * @param article
//     * @return
//     */
//    public Article saveDefinedArticle(Article article) {
//        article.setId(IdGenerator.getLongId());
//        if (article.getContent().length() > 500) {
//            String url = FileUploadUtils.saveText(article.getContent(),multilpartPath.getHtmlPath(),domainUrl.getDomain());
//            article.setTargetUrl(url);
//            article.setContent("");
//        }
//        super.insert(article);
//        return article;
//    }
}
