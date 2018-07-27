package cn.com.geasy.marketing.domain.dto.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信联系人Dto
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxContactSecondDto implements Serializable{

    private static final long serialVersionUID = 5458432260545192749L;

    /**
     * 微信用户信息识别码(唯一)
     */
    private Long uin;
    /**
     * 加密的微信号(唯一)
     */
    private String userName;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 头像URL
     */
    private String headImgUrl;


    private Integer contactFlag;
    /**
     *
     */
    private Integer memberCount;
    /**
     *
     */
    private String memberList;
    /**
     *
     */
    private String remarkName;
    /**
     *
     */
    private Integer hideInputBarFlag;
    /**
     * 微信性别
     */
    private Integer sex;
    /**
     * 微信签名
     */
    private String signature;
    /**
     *
     */
    private Integer verifyFlag;
    /**
     *
     */
    private Integer ownerUin;
    /**
     *
     */
    private String pyInitial;
    /**
     *
     */
    private String pyQuanPin;
    /**
     *
     */
    private String remarkPyInitial;
    /**
     *
     */
    private String remarkPyQuanPin;
    /**
     *
     */
    private Integer starFriend;
    /**
     *
     */
    private Integer appAccountFlag;
    /**
     *
     */
    private Integer statues;
    /**
     *
     */
    private Integer attrStatus;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     *
     */
    private String alias;
    /**
     *
     */
    private Integer snsFlag;
    /**
     *
     */
    private Integer uniFriend;
    /**
     *
     */
    private String displayName;
    /**
     *
     */
    private Integer chatRoomId;
    /**
     *
     */
    private String keyWord;
    /**
     *
     */
    private String wncryChatRoomId;

    private String encryChatRoomId;



}
