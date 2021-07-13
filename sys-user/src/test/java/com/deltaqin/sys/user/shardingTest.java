package com.deltaqin.sys.user;

import com.deltaqin.sys.mapper.SysAdminMapper;
import com.deltaqin.sys.mapper.SysCompanyMapper;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysCompany;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author deltaqin
 * @date 2020/9/29 12:49 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysUserApplication.class})
public class shardingTest {

    @Autowired
    private SysAdminMapper sysAdminMapper;
    @Autowired
    private SysCompanyMapper sysCompanyMapper;


    @Test
    public void testInsertOrder(){
        for(int i=1;i<20;i++){
//            sysAdminMapper.insert(new SysAdmin());
            SysAdmin sysAdmin = new SysAdmin();
            sysAdmin.setPassword("111");
            sysAdmin.setCreateTime(new Date());
            sysAdmin.setNickName("delta");
            sysAdmin.setUsername("delta");
            sysAdmin.setCid(2L);
            sysAdmin.setIcon("");
            sysAdmin.setNote("");
            sysAdmin.setLoginTime(new Date());
            sysAdmin.setPhone("");
            sysAdmin.setStatus(1);

            System.out.println(sysAdminMapper.insertSelective(sysAdmin));
            System.out.println("qin"+sysAdmin.getId());
        }

    }

    @Test
    public void testInsertCom (){
        for(int i=1;i<20;i++){
//            sysAdminMapper.insert(new SysAdmin());
            SysCompany sysCompany = new SysCompany();
            sysCompany.setName(i+"");
            sysCompany.setPassword("111");

            System.out.println(sysCompanyMapper.insertSelective(sysCompany));
//            System.out.println("qin"+sysCompanyMapper.getId());
        }

    }
}
