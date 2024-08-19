package com.levmin.springshiro.config;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "MyRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份（用户名）
        String principal =(String) authenticationToken.getPrincipal();
        //凭证（密码）
        String credentials =new String((char[])authenticationToken.getCredentials());
        //根据用户名密码到数据库中查询，如果不存在 就报错
        if (!"zhangyue".equals(principal)) {
            throw new UnknownAccountException();
        }
        if (!"0119".equals(credentials)){
            throw new IncorrectCredentialsException();
        }
        //如果存在就成功，返回一个AuthenticationInfo
        return new SimpleAuthenticationInfo(principal,credentials,getName());
    }
}
