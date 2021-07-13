package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysResource;
import com.deltaqin.sys.model.SysResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysResourceMapper {
    long countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}