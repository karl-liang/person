package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.CodeRecordVo;
import java.util.Date;
import java.util.List;

public interface ICodeRecordService {
    CodeRecordVo getCodeRecord(int id);

    int addCodeRecord(CodeRecordVo vo);

    int deleteCodeRecord(int id);

    int updateCodeRecord(CodeRecordVo vo);

    ResponseData<List<CodeRecordVo>> findCodeRecordList(CodeRecordVo example, Date startDate, Date endDate, int pageNum, int pageSize);
}