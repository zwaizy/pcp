package com.vcread.pcp.sevice;


import com.vcread.pcp.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}