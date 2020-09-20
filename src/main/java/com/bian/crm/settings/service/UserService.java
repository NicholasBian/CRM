package com.bian.crm.settings.service;

import com.bian.crm.exception.LoginException;
import com.bian.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
