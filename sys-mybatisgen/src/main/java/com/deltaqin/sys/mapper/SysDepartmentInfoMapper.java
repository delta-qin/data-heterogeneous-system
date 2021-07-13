package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysDepartmentInfo;
import com.deltaqin.sys.model.SysDepartmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDepartmentInfoMapper {
    long countByExample(SysDepartmentInfoExample example);

    int deleteByExample(SysDepartmentInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDepartmentInfo record);

    int insertSelective(SysDepartmentInfo record);

    List<SysDepartmentInfo> selectByExample(SysDepartmentInfoExample example);

    SysDepartmentInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysDepartmentInfo record, @Param("example") SysDepartmentInfoExample example);

    int updateByExample(@Param("record") SysDepartmentInfo record, @Param("example") SysDepartmentInfoExample example);

    int updateByPrimaryKeySelective(SysDepartmentInfo record);

    int updateByPrimaryKey(SysDepartmentInfo record);
}