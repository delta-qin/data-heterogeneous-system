package com.deltaqin.sys.remote_data.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysAdminLoginLogMapperExtend {
    @Select("select count(*) from sys_admin_login_log where date(create_time) >= CURDATE() and cid = #{cid,jdbcType=BIGINT}")
    int getTodayLoginNum(Long cid);

    @Select("select count(id) as value,address as name from sys_remote_influx_info where cid = #{cid,jdbcType=BIGINT} group by address")
    List<Map<String,String>> getSourceMap(Long cid);
}
