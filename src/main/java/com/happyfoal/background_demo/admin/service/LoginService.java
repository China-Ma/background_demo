package com.happyfoal.background_demo.admin.service;

import com.happyfoal.background_demo.admin.entity.Login;

import java.util.List;

/**
 * @author majiaju
 * @date
 */
public interface LoginService {

    boolean register(Login login);

    boolean login(Login login);

    boolean changePwd(Login login);

    List selectAll();
}
