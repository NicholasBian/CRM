package com.bian.crm.workbench.dao;

import com.bian.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> getListByClueId(String clueId);

    int delete(ClueRemark clueRemark);

    int getCountByCids(String[] ids);

    int deleteByCids(String[] ids);
}
