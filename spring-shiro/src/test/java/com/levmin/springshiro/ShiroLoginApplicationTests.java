//package com.levmin.springshiro;
//
//import com.levmin.springshiro.mapper.SystemUserMapper;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.codec.Hex;
//import org.apache.shiro.config.IniSecurityManagerFactory;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.util.Factory;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//
//@Slf4j
//@SpringBootTest
//class ShiroLoginApplicationTests {
//
//    @Resource
//    private SystemUserMapper systemUserMapper;
//
//
//    @Test
//    void contextLoads() {
//    }
//
//    /**
//     * 身份认证流程
//     * 1、首先调用Subject.login(token) 进行登录，委托给Security Manage，调用之前必须通过 SecurityUtils.setSecurityManager()设置。
//     * 2、SecurityManager负责真正的身份验证逻辑，委托给Authenticator进行身份验证
//     * 3、Authenticator 验证身份
//     * 4、Authenticator 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认 ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证
//     * 5、Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回 / 抛出异常表示身份验证成功了。此处可以配置多个 Realm，将按照相应的顺序及策略进行访问
//     */
//    @Test
//    void shiroLogin() {
//        //将shiro配置文件初始化到权限管理器中
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangyue", "0119");
//        try {
//            subject.login(token);
//            log.info("登录成功！！");
//        } catch (AuthenticationException e) {
//            log.error(e.getMessage());
//            log.error("用户登录失败");
//        }
//        Assert.assertEquals(true, subject.isAuthenticated());
//        //退出
//        subject.logout();
//    }
//
//    /**
//     * 用户角色
//     */
//    @Test
//    void shiroRoleTest() {
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangyue", "0119");
//        try {
//            subject.login(token);
//            log.info("登录成功！！");
//            subject.checkRole("role1");
//            //判断登录用户是否有role1的角色
//            Assert.assertTrue(subject.hasRole("role1"));
//            //哦按段是否有role1和role2的角色
//            Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
//            boolean b = subject.hasAllRoles(Arrays.asList("role1", "role2", "role3"));
//            System.out.println(b);
//        } catch (AuthenticationException e) {
//            log.error(e.getMessage());
//            log.error("用户登录失败");
//        }
//    }
//
//    @Test
//    void permittedTest() {
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangyue", "0119");
//        try {
//            subject.login(token);
//            //判断拥有权限：user:create
//            Assert.assertTrue(subject.isPermitted("user:create"));
//            //判断拥有权限：user:update and user:delete
//            Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
//            //判断没有权限：user:view
//            Assert.assertFalse(subject.isPermitted("user:view"));
//        } catch (AuthenticationException e) {
//            log.error(e.getMessage());
//            log.error("用户登录失败");
//        }
//    }
//
//
//    @Test
//    void encodingPassword() {
//        String str = "hello123";
//        String s = Base64.encodeToString(str.getBytes());
//        System.out.println("加密后的数据:" + s);
//        String s1 = Base64.decodeToString(s);
//        System.out.println("解密后的数据：" + s1);
//
//        String base64Encoded = Hex.encodeToString(str.getBytes());
//        System.out.println("16进制加密后的数据：" + base64Encoded);
//        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
//        System.out.println("16进制解密后的数据：" + str2);
//
//
//    }
//
//
//
//}
