package com.bian.crm.settings.service.impl;

import com.bian.crm.settings.dao.UserDao;
import com.bian.crm.settings.service.UserService;
import com.bian.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
}
