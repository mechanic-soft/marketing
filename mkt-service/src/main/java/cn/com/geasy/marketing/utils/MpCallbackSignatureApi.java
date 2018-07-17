package cn.com.geasy.marketing.utils;


import com.gitee.mechanic.core.utils.EncodeUtil;
import com.gitee.mechanic.core.utils.HashUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务号签名校验回调
 * Created by fjh on 2017/7/18.
 */
public class MpCallbackSignatureApi {

//    private static XmlMapper xmlMapper;
//
//    static {
//        xmlMapper = new XmlMapper();
//    }

    /**
     * 服务号签名验证
     * @param signature 微信加密签名。signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 验证是否成功
     */
    public static boolean signature(String signature, String timestamp, String nonce,String token) {

        String[] arr = {token, timestamp, nonce};
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        String encrpt = EncodeUtil.encodeHex(HashUtil.sha1(sb.toString()));

        // 确认请求来至微信
        return encrpt.equalsIgnoreCase(signature);
    }


    /**
     * 获取微信回调解析成map
     * @param inputStream
     * @return
     */
    public static Map getCallbackMessage(InputStream inputStream) {
        //将解析的结果存储在hashmap中
        Map<String, String> map = new HashMap<String, String>();

//        return xmlMapper.fromXml(inputStream, Map.class);

        //读取输入流
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //得到xml元素
        org.dom4j.Element root = document.getRootElement();
        //得到所有根元素的所有子节点
        List<Element> elementList = root.elements();
        //遍历所有子节点
        for (org.dom4j.Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        //释放资源
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = null;
        return map;
    }
}
