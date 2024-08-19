package com.levmin.springshiro.service.impl;

import com.levmin.springshiro.entity.SystemUser;
import com.levmin.springshiro.mapper.SystemUserMapper;
import com.levmin.springshiro.service.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser selectById(Integer id) {
        return systemUserMapper.selectByPrimaryKey(id);
    }
}
