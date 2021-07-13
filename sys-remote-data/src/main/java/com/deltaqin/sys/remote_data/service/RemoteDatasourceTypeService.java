package com.deltaqin.sys.remote_data.service;

import java.util.HashMap;

public interface RemoteDatasourceTypeService {
    String getTypeByID(Long id);

    HashMap<String,Object> getAllType();
}
