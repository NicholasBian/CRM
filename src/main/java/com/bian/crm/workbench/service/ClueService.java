package com.bian.crm.workbench.service;

import com.bian.crm.vo.PaginationVO;
import com.bian.crm.workbench.domain.Clue;
import com.bian.crm.workbench.domain.Tran;

import java.util.Map;

public interface ClueService {
    boolean save(Clue c);

    Clue detail(String id);

    boolean unbund(String id);

    boolean bund(String cid, String[] aids);

    boolean convert(String clueId, Tran t, String createBy);

    PaginationVO<Clue> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndClue(String id);

    boolean update(Clue c);
}
