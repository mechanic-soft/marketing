package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.contant.MpMessageConstants;
import com.gitee.mechanic.json.mapper.JsonMapper;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息
 * Created by fjh on 2017/8/10.
 */
public class MessageTemplateUtil {

    private static JsonMapper jsonMapper;

    static {
        jsonMapper = new JsonMapper();
    }
    /**
     * 获取模板消息体 json 字符串
     * @param openId
     * @param templateId
     * @param url
     * @param details
     * @return
     */
    public static String getTemplateMessage(
            String openId, String templateId, String url, Map<String, String> details) {


        Map<String, Object> map = Maps.newHashMap();
        map.put(MpMessageConstants.MTP_TOUSER, openId);
        map.put(MpMessageConstants.MTP_TEMPLATE_ID, templateId);
        map.put(MpMessageConstants.MTP_URL, url);
        try {

            Map<String, Object> dataMap = Maps.newHashMap();
            for (Map.Entry<String, String> entry : details.entrySet()) {
                Map<String, Object> jsonDataMap = Maps.newHashMap();
                jsonDataMap.put(MpMessageConstants.MTP_VALUE, entry.getValue());
                dataMap.put(entry.getKey(), jsonDataMap);
            }
            map.put(MpMessageConstants.MTP_DATA, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonMapper.toJson(map);
    }

    /**
     * 模板消息体的获取
     * 模板的key 是按照keyword1 ，keyword2 ，keyword3 ...的规律 定义
     * @param first
     * @param remark
     * @param data
     * @return
     */
    public static Map<String, String> getMessageDetailByKeyword(
            String first, String remark, String... data) {

        Map<String, String> details = new HashMap<String, String>();
        details.put(MpMessageConstants.MTP_FIRST, first);
        for (int i = 0; i < data.length; i++) {
            int j = i + 1;
            String key = MpMessageConstants.MTP_KEYWORD + j;
            details.put(key, data[i]);
        }
        details.put(MpMessageConstants.MTP_REMARK, remark);
        return details;
    }
}
