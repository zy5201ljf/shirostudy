package com.levmin.shiro.dao;

import com.levmin.shiro.util.JdbcTemplateUtils;
import com.levmin.shiro.util.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

public class MySessionDAO extends CachingSessionDAO {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();

    /**
     * 更新会话
     * @param session the session object whose state will be propagated to the EIS.
     */
    @Override
    protected void doUpdate(Session session) {
        //如果会话过期，就停止更新
        if (session instanceof ValidatingSession
        && ((ValidatingSession) session).isValid()) {
            return;
        }
        String sql = "update sys_session set session_state=? where session_id=?";
        jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());
    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = generateSessionId(session);
        assignSessionId(session,serializable);
        String sql = "insert into sys_session values(?,?,?)";
        jdbcTemplate.update(sql,session.getId(), SerializableUtils.serialize(session));

        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}
