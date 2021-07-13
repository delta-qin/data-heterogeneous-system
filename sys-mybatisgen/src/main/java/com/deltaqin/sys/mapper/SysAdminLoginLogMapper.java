package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysAdminLoginLog;
import com.deltaqin.sys.model.SysAdminLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAdminLoginLogMapper {
    long countByExample(SysAdminLoginLogExample example);

    int deleteByExample(SysAdminLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAdminLoginLog record);

    int insertSelective(SysAdminLoginLog record);

    List<SysAdminLoginLog> selectByExample(SysAdminLoginLogExample example);

    SysAdminLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAdminLoginLog record, @Param("example") SysAdminLoginLogExample example);

    int updateByExample(@Param("record") SysAdminLoginLog record, @Param("example") SysAdminLoginLogExample example);

    int updateByPrimaryKeySelective(SysAdminLoginLog record);

    int updateByPrimaryKey(SysAdminLoginLog record);
}