package com.deltaqin.sys.user.service.impl;

import com.deltaqin.sys.common.utils.RedisService;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.user.service.SysAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author deltaqin
 * @date 2020/9/8 7:07 下午
 */
@Service
public class SysAdminCacheServiceImpl implements SysAdminCacheService {
    @Autowired(required = false)
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;

    @Override
    public void delAdmin(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public SysAdmin getAdmin(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        return (SysAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(SysAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getId();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
}
