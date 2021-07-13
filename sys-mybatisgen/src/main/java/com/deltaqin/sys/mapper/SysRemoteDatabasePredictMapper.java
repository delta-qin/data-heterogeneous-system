package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteDatabasePredict;
import com.deltaqin.sys.model.SysRemoteDatabasePredictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteDatabasePredictMapper {
    long countByExample(SysRemoteDatabasePredictExample example);

    int deleteByExample(SysRemoteDatabasePredictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteDatabasePredict record);

    int insertSelective(SysRemoteDatabasePredict record);

    List<SysRemoteDatabasePredict> selectByExample(SysRemoteDatabasePredictExample example);

    SysRemoteDatabasePredict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteDatabasePredict record, @Param("example") SysRemoteDatabasePredictExample example);

    int updateByExample(@Param("record") SysRemoteDatabasePredict record, @Param("example") SysRemoteDatabasePredictExample example);

    int updateByPrimaryKeySelective(SysRemoteDatabasePredict record);

    int updateByPrimaryKey(SysRemoteDatabasePredict record);
}