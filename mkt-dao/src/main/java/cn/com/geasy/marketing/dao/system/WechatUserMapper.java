///*
// * Copyright (c) 2004-2015 by KYD All rights reserved
// */
//package cn.com.geasy.marketing.dao.system;
//
//import com.tvi.BaseMapper;
//import com.tvi.pojo.dto.user.UserWechatRipeDto;
//import com.tvi.pojo.entity.user.WechatUser;
//import com.tvi.pojo.entity.user.WechatUserInfoList;
//import com.tvi.pojo.vo.user.WechatUserInfoVo;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * <b><code>WechatUserMapper</code></b>
// * <p>
// * 微信个人信息Mapper
// * </p>
// * <b>Creation Time:</b> 2018-01-12 16:13:41
// * @author
// * @version quxue 1.0
// */
//@Repository
//public interface WechatUserMapper extends BaseMapper<WechatUser> {
//    WechatUser findByOpenId(String openId);
//
//    List<WechatUser> findBySubcribeWechatUser(Long userId);
//
//    Integer countByThisWeek(Long userId);
//
//    Integer countByToday(Long userId);
//
//    Integer countTotal(Long userId);
//
//    List<WechatUser> findByThisWeek(Long userId);
//
//    List<WechatUser>  findByToday(Long userId);
//
//    List<WechatUser>  findTotal(Long userId);
//
//    Integer countByTime(@Param("userId") Long userId, @Param("time") Date time);
//
//    List<WechatUser> findByOwnerUserId(@Param("userId") Long userId, @Param("nickName") String nickName);
//
//    List<UserWechatRipeDto> findRipeUser(@Param("userId") Long userId, @Param("nickName") String nickName, @Param("type") Integer type);
//
//    List<WechatUserInfoList> findWechatUserList(WechatUserInfoVo wechatUserInfoVo);
//
//    List<WechatUser> findWechatUserManager();
//
//    List<WechatUser> findWechatUserBysubcriptionUserId(Long userId);
//
//    List<WechatUser> findOwnSubscribeUserByUserId(Long userId);
//
//}