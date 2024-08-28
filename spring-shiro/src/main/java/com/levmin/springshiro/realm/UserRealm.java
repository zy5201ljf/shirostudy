package com.levmin.springshiro.realm;

import com.levmin.springshiro.entity.SysUsers;
import com.levmin.springshiro.service.SysUsersService;
import com.levmin.springshiro.service.impl.SysUsersImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UserRealm extends AuthorizingRealm {

    private SysUsersService sysUsersService = new SysUsersImpl();

    /**
     * 用户身份认证
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //身份，姓名用户名
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        simpleAuthorizationInfo.setRoles(sysUsersService.findRoles(sysUsers));
        Set<String> permission = new HashSet<>();
        permission.add("admin");
        simpleAuthorizationInfo.setStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        SysUsers sysUsers = sysUsersService.findByUsername(principal);
        if (sysUsers == null) {
            throw new UnknownAccountException();
        }
        if (Boolean.TRUE.equals(sysUsers.getLocked())) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                sysUsers.getUsername(),
                sysUsers.getPassword(),
                ByteSource.Util.bytes(sysUsers.getCredentialsSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }

    public static String test(String msg) {
        System.out.println(msg);
        return msg;
    }


    public static String test01(String msg) {
        System.out.println(msg);
        return msg;
    }

    public static void main(String[] args) {
//        test01("你好！！");
//        CompletableFuture.runAsync(() -> test("nihao!!"));
//        test01("你好！！");


        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE,5);

        Date time = instance.getTime();
        System.out.println(time );


    }

}
