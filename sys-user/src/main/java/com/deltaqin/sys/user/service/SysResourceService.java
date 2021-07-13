package com.deltaqin.sys.user.service;

import com.deltaqin.sys.model.SysResource;

import java.util.List;
import java.util.Map;

/**
 * @author deltaqin
 * @date 2020/9/9 10:56 下午
 */
public interface SysResourceService {
    /**
     * 初始化资源角色规则
     */
    Map<String,List<String>> initResourceRolesMap();

//    /**
//     * 添加资源
//     */
//    int create(SysResource umsResource);
//
//    /**
//     * 修改资源
//     */
//    int update(Long id, SysResource umsResource);
//
//    /**
//     * 获取资源详情
//     */
//    SysResource getItem(Long id);
//
//    /**
//     * 删除资源
//     */
//    int delete(Long id);
//
//    /**
//     * 分页查询资源
//     */
//    List<SysResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);
//
//    /**
//     * 查询全部资源
//     */
//    List<SysResource> listAll();


}
