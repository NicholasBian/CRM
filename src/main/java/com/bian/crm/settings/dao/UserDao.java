package com.bian.crm.settings.dao;

import com.bian.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
