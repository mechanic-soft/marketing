package cn.com.geasy.marketing.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配
 * Created by fjh on 2017/7/26.
 */
public class RegExpUtils {

    /**
     * 关键key 查找 对应键值对
     *
     * @param key
     * @param body
     * @return
     */
    public static String getKeyValueBykey(String key, String body) {
        // 正则初始化
        Pattern p = Pattern.compile(key + "([\\s\\S]*?)\";");
        // 匹配器初始化
        Matcher m = p.matcher(body);
        // 匹配查询
        while (m.find()) {
            System.out.println(m.group());
            String r = m.group();
            r = r.replace(",", "");
            String rs[] = r.split("=");
            if (rs.length > 0) {
                return rs[1].replace("\"", "").replace(";", "").trim();
            }
        }
        return "";
    }

    /**
     * 关键key 查找 标签值
     *
     * @param key
     * @param body
     * @return
     */
    public static String getTagBykey(String key, String body) {
        // 正则初始化
        Pattern p = Pattern.compile(key + "([\\s\\S]*?)</");
        // 匹配器初始化
        Matcher m = p.matcher(body);
        // 匹配查询
        while (m.find()) {
            System.out.println(m.group());
            String r = m.group();
            r = r.replace("</", "").replace(">", "").replace(key, "");
            return r;
        }
        return "";
    }

    /**
     * 获取分享icon
     * @param body
     * @return
     */
    public static String getIcon(String body) {
        // 正则初始化
        Pattern p = Pattern.compile("imgUrl: \""+ "([\\s\\S]*?)\"");
        // 匹配器初始化
        Matcher m = p.matcher(body);
        String r="";
        // 匹配查询
        if (m.find(1)) {
            System.out.println(m.group());
             r = m.group();
             int start = r.indexOf("http");
             r = r.substring(start).replaceAll("\"","");
        }
        return r;
    }

    /**
     * 防盗链图片的转成中间路径 存到文章内容中
     *
     * @param content
     * @return
     */
    public static String mpArticleChangeImges(String content) {

        //处理图片的路径
        // 正则初始化
        Pattern p = Pattern.compile("data-src=\"" + "([\\s\\S]*?)\"");
        // 匹配器初始化
        Matcher m = p.matcher(content);
        // 匹配查询
        while (m.find()) {
            System.out.println(m.group());
            String r = m.group();
            String imgUrl = r.replaceAll("data-src=\"", "").replace("\"", "");
            ;
            String rp = "data-src=\"" + DomainUtils.domainBaseUrl() + "/article/change/image?url=" + imgUrl + "\"";
            content = content.replace(m.group(), rp);
        }

        //处理图片的路径
        // 正则初始化
        Pattern p1 = Pattern.compile("background-image([\\s\\S]*?)\\);");
        // 匹配器初始化
        Matcher m1 = p1.matcher(content);
        // 匹配查询
        while (m1.find()) {
            String r = m1.group();
            if (r.contains("data:image/png;base64")) {
                continue;
            }

            int start = r.indexOf("http");
            int end = r.indexOf(")");
            if (start == -1 || end == -1) {
                continue;
            }
            r = r.substring(start, end);
            r = r.replaceAll(";", "").replaceAll("&quot", "");
            //String rp="background-image: url"+DomainUtil.domainBaseUrl()+"/article/change/image?url="+r+"\";";
            String rp = DomainUtils.domainBaseUrl() + "/article/change/image?url=" + r;
            content = content.replace(r, rp);

        }
        return content;
    }

    /**
     * 去掉微信文章自带的js代码
     *
     * @param content
     * @return
     */
    public static String filterMpArticleJsCode(String content) {
        //去掉微信文章自带的js代码
        // 正则初始化
        Pattern p1 = Pattern.compile("<script([\\s\\S]*?)</script>");
        // 匹配器初始化
        Matcher m1 = p1.matcher(content);
        // 匹配查询
        while (m1.find()) {
            System.out.println(m1.group());
            String r = m1.group();
            content = content.replace(r, "");
        }
        return content;
    }

    public static Matcher fingMatcher(String content,String reg) {
        // 正则初始化
        Pattern p1 = Pattern.compile(reg);
        // 匹配器初始化
        Matcher m1 = p1.matcher(content);
        return m1;
    }

    public static String replace(String content,String reg,String headStr) {
        Matcher m1 = fingMatcher(content,reg);
        List<String> replaces= new ArrayList();
        while (m1.find()) {
            System.out.println(m1.group());
            String r = m1.group();

            if(!replaces.contains(r)){
                replaces.add(r);
            }
        }
        for(String s: replaces){
            content = content.replaceAll(s, headStr+s);
        }
        return content;
    }

    public static String find(String content,String reg){
        // 正则初始化
        Pattern p1 = Pattern.compile(reg);
        // 匹配器初始化
        Matcher m1 = p1.matcher(content);
        // 匹配查询
        while (m1.find()) {
            System.out.println(m1.group());
            return  m1.group();
        }
        return "";
    }
}
