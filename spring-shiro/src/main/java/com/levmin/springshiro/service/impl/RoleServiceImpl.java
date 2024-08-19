package com.levmin.springshiro.service.impl;

import com.levmin.springshiro.entity.SysPermissions;
import com.levmin.springshiro.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 创建权限角色
     * @param permission
     * @return
     */
    @Override
    public SysPermissions createPermission(SysPermissions permission) {
        return null;
    }

    /**
     * 删除权限
     * @param permissionId
     */
    @Override
    public void deletePermission(Long permissionId) {

    }
}
