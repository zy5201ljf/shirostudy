package com.levmin.springshiro.service;


import com.levmin.springshiro.entity.SysPermissions;

public interface RoleService {
    public SysPermissions createPermission(SysPermissions permission);
    public void deletePermission(Long permissionId);
}
