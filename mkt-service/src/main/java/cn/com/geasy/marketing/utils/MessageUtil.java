package cn.com.geasy.marketing.utils;


import cn.com.geasy.marketing.domain.dto.wechat.message.NewsMessage;
import cn.com.geasy.marketing.domain.dto.wechat.message.TextMessage;
import cn.com.geasy.marketing.domain.entity.article.Article;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * 微信消息回复，bean 转换成 xml
 * Created by fjh on 2017/7/25.
 */
public class MessageUtil {
//    private static XmlMapper xmlMapper;
//
//    static {
//        xmlMapper = new XmlMapper();
//    }
    /**
     * 文本消息对象转换成xml
     */

    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图文信息对象转换成xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);

    }

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                //
                boolean cdata = true;

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
