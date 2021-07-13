package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteDatabaseType;
import com.deltaqin.sys.model.SysRemoteDatabaseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteDatabaseTypeMapper {
    long countByExample(SysRemoteDatabaseTypeExample example);

    int deleteByExample(SysRemoteDatabaseTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteDatabaseType record);

    int insertSelective(SysRemoteDatabaseType record);

    List<SysRemoteDatabaseType> selectByExample(SysRemoteDatabaseTypeExample example);

    SysRemoteDatabaseType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteDatabaseType record, @Param("example") SysRemoteDatabaseTypeExample example);

    int updateByExample(@Param("record") SysRemoteDatabaseType record, @Param("example") SysRemoteDatabaseTypeExample example);

    int updateByPrimaryKeySelective(SysRemoteDatabaseType record);

    int updateByPrimaryKey(SysRemoteDatabaseType record);
}