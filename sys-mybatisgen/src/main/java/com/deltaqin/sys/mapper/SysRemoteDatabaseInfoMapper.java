package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteDatabaseInfo;
import com.deltaqin.sys.model.SysRemoteDatabaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteDatabaseInfoMapper {
    long countByExample(SysRemoteDatabaseInfoExample example);

    int deleteByExample(SysRemoteDatabaseInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteDatabaseInfo record);

    int insertSelective(SysRemoteDatabaseInfo record);

    List<SysRemoteDatabaseInfo> selectByExample(SysRemoteDatabaseInfoExample example);

    SysRemoteDatabaseInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteDatabaseInfo record, @Param("example") SysRemoteDatabaseInfoExample example);

    int updateByExample(@Param("record") SysRemoteDatabaseInfo record, @Param("example") SysRemoteDatabaseInfoExample example);

    int updateByPrimaryKeySelective(SysRemoteDatabaseInfo record);

    int updateByPrimaryKey(SysRemoteDatabaseInfo record);
}