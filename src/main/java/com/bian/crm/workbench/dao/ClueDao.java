package com.bian.crm.workbench.dao;

import com.bian.crm.workbench.domain.Clue;

public interface ClueDao {


    int save(Clue c);

    Clue detail(String id);

}
