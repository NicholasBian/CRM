package com.bian.crm.workbench.service.impl;

import com.bian.crm.utils.SqlSessionUtil;
import com.bian.crm.workbench.dao.CustomerDao;
import com.bian.crm.workbench.domain.Customer;
import com.bian.crm.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);

    public List<String> getCustomerName(String name) {

        List<String> sList = customerDao.getCustomerName(name);

        return sList;
    }
}
