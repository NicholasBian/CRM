package com.bian.crm.settings.service;

import com.bian.crm.exception.LoginException;
import com.bian.crm.settings.domain.User;

import java.util.List;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
