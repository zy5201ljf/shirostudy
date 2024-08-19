package com.levmin.springshiro.mapper;

import com.levmin.springshiro.entity.SysUsers;
import com.levmin.springshiro.entity.SysUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUsersMapper extends SysUsersRepository{
    int countByExample(SysUsersExample example);

    int deleteByExample(SysUsersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    List<SysUsers> selectByExample(SysUsersExample example);

    SysUsers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUsers record, @Param("example") SysUsersExample example);

    int updateByExample(@Param("record") SysUsers record, @Param("example") SysUsersExample example);

    int updateByPrimaryKeySelective(SysUsers record);

    int updateByPrimaryKey(SysUsers record);
}