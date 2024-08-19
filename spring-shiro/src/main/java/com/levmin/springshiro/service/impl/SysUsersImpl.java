package com.levmin.springshiro.service.impl;

import com.levmin.springshiro.config.PasswordHelper;
import com.levmin.springshiro.entity.SysUsers;
import com.levmin.springshiro.entity.SysUsersExample;
import com.levmin.springshiro.mapper.SysUsersMapper;
import com.levmin.springshiro.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class SysUsersImpl implements SysUsersService {

    private PasswordHelper passwordHelper = new PasswordHelper();
    @Autowired
    private SysUsersMapper sysUsersMapper;

    /**
     * 创建用户
     *
     * @param sysUsers
     * @return
     */
    @Override
    public SysUsers createUser(SysUsers sysUsers) {
        passwordHelper.encryptPassword(sysUsers);
        sysUsersMapper.insert(sysUsers);
        return sysUsers;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public Set<String> findRoles(SysUsers username) {
        return sysUsersMapper.findRolesByUser(username);
    }

    @Override
    public SysUsers findByUsername(String username) {
        SysUsersExample sysUsersExample = new SysUsersExample();
        SysUsersExample.Criteria criteria = sysUsersExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(sysUsersExample);
        return sysUsers.get(0);
    }

    @Override
    @Async
    public String test(String msg)  {
        try {
            System.out.println(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return msg;
    }
}
