package com.vcread.pcp.sevice.impl;


import com.vcread.pcp.dao.UserInfoDao;
import com.vcread.pcp.entity.UserInfo;
import com.vcread.pcp.sevice.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}