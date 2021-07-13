package com.deltaqin.sys.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.constant.CodeConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.common.entities.User;
import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.common.utils.NumberUtils;
import com.deltaqin.sys.mapper.SysAdminLoginLogMapper;
import com.deltaqin.sys.mapper.SysAdminMapper;
import com.deltaqin.sys.mapper.SysAdminRoleRelationMapper;
import com.deltaqin.sys.model.*;
import com.deltaqin.sys.user.dao.SysAdminRoleRelationDao;
import com.deltaqin.sys.user.dao.SysLoginLogDao;
import com.deltaqin.sys.user.dto.*;
import com.deltaqin.sys.user.service.FeignAuthService;
import com.deltaqin.sys.user.service.SysAdminCacheService;
import com.deltaqin.sys.user.service.SysAdminService;
import com.deltaqin.sys.user.service.SysCompanyService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author deltaqin
 * @date 2020/9/8 2:58 下午
 */
@Service
@Slf4j
public class SysAdminServiceImpl implements SysAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysAdminServiceImpl.class);

    @Autowired(required = false)
    private SysLoginLogDao sysLoginLogDao;
    @Autowired(required = false)
    private SysAdminMapper adminMapper;
    @Autowired(required = false)
    private SysAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired(required = false)
    private SysAdminRoleRelationDao adminRoleRelationDao;
    @Autowired(required = false)
    private SysAdminLoginLogMapper loginLogMapper;
    @Autowired
    private FeignAuthService authService;
    @Autowired
    private SysAdminCacheService adminCacheService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private SysCompanyService sysCompanyService;

    static final String KEY_PREFIX = "user:code:phone:";

    @Override
    public Boolean sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            // 发送短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            // 坑爹啊，这里写的时候routing key和消费者那里的不一样，我说为什么一直不消费
            this.amqpTemplate.convertAndSend("sys.sms.exchange", "sms.verify.code", msg);
            // 将code存入redis
            this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            log.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }

    @Override
    public SysAdmin register(SysAdminParam sysAdminParam, String code, String ccode) throws ValidateCodeException {
        String s = this.redisTemplate.opsForValue().get(KEY_PREFIX + sysAdminParam.getPhone());
        if (s == null){
            throw new ValidateCodeException("验证码过期");
        }else if (!s.equals(code)){
            //验证码错误
            throw new ValidateCodeException("验证码错误");
        }

        SysCompany byID = sysCompanyService.getByID(sysAdminParam.getCid());
        if (byID == null){
            throw new ValidateCodeException("公司不存在");
        } else if (!BCrypt.checkpw(ccode ,byID.getPassword())){
            throw new ValidateCodeException("公司校验码错误，联系公司超级管理员获取！");
        }

        SysAdmin sysAdmin = new SysAdmin();
        BeanUtils.copyProperties(sysAdminParam, sysAdmin);
        sysAdmin.setCreateTime(new Date());
        sysAdmin.setStatus(1);
        sysAdmin.setIcon("http://res.deltaqin.com/20200407123458.jpg");
        //查询是否有相同用户名的用户
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(sysAdmin.getUsername());
        List<SysAdmin> sysAdminList = adminMapper.selectByExample(example);
        if (sysAdminList.size() > 0) {
            throw new ValidateCodeException("用户名已被占用");
        }

        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(sysAdmin.getPassword());
        sysAdmin.setPassword(encodePassword);
        adminMapper.insertSelective(sysAdmin);
        List<Long> list = new ArrayList<>();
        list.add((long) 2);
        updateRole(sysAdmin.getId(), list);
        return sysAdmin;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        SysAdminRoleRelationExample adminRoleRelationExample = new SysAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysAdminRoleRelation roleRelation = new SysAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public CommonResult login(String username, String password, Long cid) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            throw new RuntimeException("用户名或密码不能为空！");
        }

        // 这里验证一下公司，下面的auth就当做是获取token吧，其实一开始应该直接用utils，oauth其实搞的麻烦了，还要自己定义很多东西
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(username).andCidEqualTo(cid);
        List<SysAdmin> sysAdmins = adminMapper.selectByExample(example);
        System.out.println(sysAdmins.size() +"sysAdmins.size()");
        if (sysAdmins == null || sysAdmins.size() == 0) {
            throw new RuntimeException("用户名不存在或公司选择错误！");
        }

        // 准备数据给auth获取token
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret","deltaqin");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        params.put("cid", cid+"");
        CommonResult restResult = authService.getAccessToken(params);
        if(CodeConstant.SUCCESS.getCode()==restResult.getCode()&&restResult.getData()!=null){
            SysAdmin admin = getAdminByUsername(username);
            insertLoginLog(admin);
            updateLoginTimeByUsername(admin);
        }
        if (restResult.getCode()>=400){
            return CommonResult.failed(restResult.getMessage().replaceAll("org.springframework.security.authentication.InternalAuthenticationServiceException:",""));
        }
        return restResult;
    }

    @Override
    public SysAdmin getAdminByUsername(String username) {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        //这里不能加cid，否则cid 在鉴权的时候不知道cid,是重写的方法，获取不到cid
        List<SysAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    /**
     * 添加登录记录
     */
    private void insertLoginLog(SysAdmin admin) {
        SysAdminLoginLog loginLog = new SysAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCid(admin.getCid());
        loginLog.setCreateTime(new Date());
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        //根据ip进行位置信息搜索

        DbConfig config = null;
        try {
            config = new DbConfig();
            //获取ip库的位置（放在src下）（直接通过测试类获取文件Ip2RegionTest为测试类）
            String dbfile = SysAdminServiceImpl.class.getResource("/ip2region/ip2region.db").getPath();
            DbSearcher searcher = new DbSearcher(config, dbfile);
            //采用Btree搜索
            DataBlock block = searcher.btreeSearch(request.getRemoteAddr());
            //打印位置信息（格式：国家|大区|省份|城市|运营商）
            String region = block.getRegion();
            StringBuilder group = new StringBuilder();
            String[] split = region.split("\\|");
//        System.out.println(split.toString());
            for (int i=0; i < split.length; i++){
//            System.out.println(split[i]);
                if (i ==2 | i==3) group.append(split[i]+"|");
            }
            loginLog.setAddress(String.valueOf(group));
        } catch (DbMakerConfigException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginLogMapper.insertSelective(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(SysAdmin admin) {
        SysAdmin record = new SysAdmin();
        record.setLoginTime(new Date());
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        example.createCriteria().andCidEqualTo(admin.getCid());
        adminMapper.updateByExampleSelective(record, example);
    }

    /**
     * 给auth使用
     * @param username
     * @return
     */
    @Override
    public User loadUserByUsername(String username){

        //获取用户信息
        SysAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<SysRole> roleList = getRoleList(admin.getId());
            User userDTO = new User();
            BeanUtils.copyProperties(admin,userDTO);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }


    // 记得删除缓存里面存放的用户,更新用户自己的信息
    @Override
    public int update(SysAdmin admin) {
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername())
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")))
                .andIdEqualTo(Long.valueOf(request.getHeader("id")));
        int count = adminMapper.updateByExampleSelective(admin, example);
//        User user = this.loadUserByUsername(admin.getUsername());
        adminCacheService.delAdmin(Long.valueOf(request.getHeader("id")));
        return count;
    }


    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if(StrUtil.isEmpty(param.getUsername())
                ||StrUtil.isEmpty(param.getOldPassword())
                ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        SysAdminExample example = new SysAdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername())
                .andIdEqualTo(Long.valueOf(request.getHeader("id")))
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")));
        List<SysAdmin> adminList = adminMapper.selectByExample(example);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        SysAdmin sysAdmin = adminList.get(0);
        if(!BCrypt.checkpw(param.getOldPassword(),sysAdmin.getPassword())){
            return -3;
        }
        sysAdmin.setPassword(BCrypt.hashpw(param.getNewPassword()));
        adminMapper.updateByPrimaryKey(sysAdmin);
        adminCacheService.delAdmin(sysAdmin.getId());
        return 1;
    }

    @Override
    public int delete(Long id) {
        // 数据库和缓存都要删
        int count = adminMapper.deleteByPrimaryKey(id);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public List<SysRole> getRoleList(Long adminId) {
        // 自定义的连表查询
        return adminRoleRelationDao.getRoleList(adminId);
    }

    // 登录之后使用
    @Override
    public SysAdmin getCurrentAdmin() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
//        String cid = request.getHeader("cid");
//        String id = request.getHeader("id");
//        String user_name = request.getHeader("user_name");
//        System.out.println(cid + id + user_name);

        if(StrUtil.isEmpty(userStr)){
            throw new RuntimeException("暂未登录或token已经过期");
        }
        User userDto = JSONUtil.toBean(userStr, User.class);
        SysAdmin admin = adminCacheService.getAdmin(userDto.getId());
        if(admin!=null){
            return admin;
        }else{
            admin = adminMapper.selectByPrimaryKey(userDto.getId());
            adminCacheService.setAdmin(admin);
            return admin;
        }
    }


//    以下API是用户信息列表以及搜索以及道路记录
    @Override
    public List<AdminRole> listAllByPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return  adminRoleRelationDao.listAllByPage();
    }

    @Override
    public List<AdminRole> list(Long id, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return  adminRoleRelationDao.getAdminRole(id);
    }

    @Override
    public int updateStatus(Long id) {
        SysAdmin sysAdmin1 = adminMapper.selectByPrimaryKey(id);
        Integer status = sysAdmin1.getStatus();
        if (status == 1) {
            sysAdmin1.setStatus(0);
        } else {
            sysAdmin1.setStatus(1);
        }
        return adminMapper.updateByPrimaryKey(sysAdmin1);
    }



    @Override
    public List<AdminRole> searchByUsername(String username, Integer pageSize, Integer pageNum) {
        if (StringUtils.isEmpty(username)){
            return null;
        }
        PageHelper.startPage(pageNum, pageSize);
        String cid = request.getHeader("cid");

        return adminRoleRelationDao.getAdminRoleByName(username, Long.valueOf(cid));

    }

//    @Override
//    public List<SysAdminLoginLog> searchLoginLog() {
////        User user = loadUserByUsername(username);
//        SysAdminLoginLogExample example = new SysAdminLoginLogExample();
//        example.createCriteria().andAdminIdEqualTo(Long.valueOf(request.getHeader("id")));
//        List<SysAdminLoginLog> adminList = loginLogMapper.selectByExample(example);
//        return adminList;
//    }

    @Override
    public List<LoginLog> searchLoginLogByDay(String username) {
        User user = loadUserByUsername(username);
        return sysLoginLogDao.getLoginLog(user.getId(), user.getCid());
    }

    @Override
    public List<SysLoginRegion> searchLoginRegion(String username) {
        User user = loadUserByUsername(username);
        return sysLoginLogDao.getRegion(user.getId());
    }

    //    @Override
//    public SysAdmin getItem(Long id) {
//        return adminMapper.selectByPrimaryKey(id);
//    }
}
