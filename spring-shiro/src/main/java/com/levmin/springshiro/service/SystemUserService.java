package com.levmin.springshiro.service;


import com.levmin.springshiro.entity.SystemUser;

public interface SystemUserService {

    SystemUser selectById(Integer id);
}
