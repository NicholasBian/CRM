package com.bian.crm.workbench.dao;

import com.bian.crm.workbench.domain.ClueActivityRelation;

public interface ClueActivityRelationDao {


    int unbund(String id);

    int bund(ClueActivityRelation car);
}
