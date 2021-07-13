package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemotePanalType;
import com.deltaqin.sys.model.SysRemotePanalTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemotePanalTypeMapper {
    long countByExample(SysRemotePanalTypeExample example);

    int deleteByExample(SysRemotePanalTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemotePanalType record);

    int insertSelective(SysRemotePanalType record);

    List<SysRemotePanalType> selectByExample(SysRemotePanalTypeExample example);

    SysRemotePanalType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemotePanalType record, @Param("example") SysRemotePanalTypeExample example);

    int updateByExample(@Param("record") SysRemotePanalType record, @Param("example") SysRemotePanalTypeExample example);

    int updateByPrimaryKeySelective(SysRemotePanalType record);

    int updateByPrimaryKey(SysRemotePanalType record);
}