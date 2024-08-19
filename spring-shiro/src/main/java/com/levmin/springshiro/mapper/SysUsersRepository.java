package com.levmin.springshiro.mapper;

import com.levmin.springshiro.entity.SysUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface SysUsersRepository {
    /**
     * 查询角色信息
     * @param sysUsers
     * @return
     */
    Set<String> findRolesByUser(SysUsers sysUsers);
}
