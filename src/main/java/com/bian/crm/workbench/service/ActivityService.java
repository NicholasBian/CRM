package com.bian.crm.workbench.service;

import com.bian.crm.vo.PaginationVO;
import com.bian.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
