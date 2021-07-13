package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAdminMapper {
    long countByExample(SysAdminExample example);

    int deleteByExample(SysAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAdmin record);

    int insertSelective(SysAdmin record);

    List<SysAdmin> selectByExample(SysAdminExample example);

    SysAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAdmin record, @Param("example") SysAdminExample example);

    int updateByExample(@Param("record") SysAdmin record, @Param("example") SysAdminExample example);

    int updateByPrimaryKeySelective(SysAdmin record);

    int updateByPrimaryKey(SysAdmin record);
}