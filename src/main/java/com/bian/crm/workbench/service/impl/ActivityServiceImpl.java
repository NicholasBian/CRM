package com.bian.crm.workbench.service.impl;

import com.bian.crm.utils.SqlSessionUtil;
import com.bian.crm.workbench.dao.ActivityDao;
import com.bian.crm.workbench.domain.Activity;
import com.bian.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public boolean save(Activity a) {

        boolean flag = true;

        int count = activityDao.save(a);
        if(count != 1){
            flag = false;
        }

        return flag;
    }
}
