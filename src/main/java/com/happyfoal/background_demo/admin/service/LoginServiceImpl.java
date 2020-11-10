package com.happyfoal.background_demo.admin.service;

import com.happyfoal.background_demo.admin.entity.Login;
import com.happyfoal.background_demo.admin.entity.LoginExample;
import com.happyfoal.background_demo.admin.mapper.LoginMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author majiaju
 * @date
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    LoginMapper loginMapper;

    @Override
    public boolean register(Login login) {

        int i = loginMapper.insert(login);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(Login login) {
        LoginExample example = new LoginExample();
        LoginExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(login.getUsername());
        criteria.andPasswordEqualTo(login.getPassword());
        List<Login> list = loginMapper.selectByExample(example);
        if (list.size()>0){
            return true;
        }
        return false;
    }
}
