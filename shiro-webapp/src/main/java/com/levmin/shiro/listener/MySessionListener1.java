package com.levmin.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener1 implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("session start");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("session stop");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("session expiration");
    }
}
