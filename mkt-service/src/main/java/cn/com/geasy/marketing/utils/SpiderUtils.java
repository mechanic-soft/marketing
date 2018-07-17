package cn.com.geasy.marketing.utils;

import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * 爬虫网页
 * Created by fjh on 2017/7/26.
 */
public class SpiderUtils {

    /**
     * 爬虫url的内容
     *
     * @param url
     * @return
     */
    public static String getSpiderText(String url) {

        HttpResponse response = HttpRequest.get(url).send();
        String body = null;
        try {
            body = new String(response.bodyBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(HttpCode.CODING_ERROR);
        }
        if (StringUtil.isNotEmpty(body)) {
            body = body.replaceAll(
                    "\"data:image/gif;base64,iVBORw0KGgoAAAANSUhE" +
                            "UgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\";",
                    "images[i].getAttribute('data-src');");
        }
        return body;
    }




}
