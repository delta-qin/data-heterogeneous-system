package com.deltaqin.sys.remote_data.service;

import com.deltaqin.sys.model.SysDepartmentInfo;

import java.util.List;

public interface DepartmentService {
    Boolean addDepartment(SysDepartmentInfo sysDepartmentInfo);

    Boolean deleteaDepartment(long id , long cid);

    Boolean updateDepartment(SysDepartmentInfo sysDepartmentInfo);

    List<SysDepartmentInfo> getDepartment(long cid);
}
