package com.levmin.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class MySessionListener2 extends SessionListenerAdapter {
    @Override
    public void onStart(Session session) {
        System.out.println("Starting MySessionListener2"+session.getId());
    }
}
