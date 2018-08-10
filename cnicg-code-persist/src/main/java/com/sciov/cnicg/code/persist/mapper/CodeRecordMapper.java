package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.CodeRecord;
import com.sciov.cnicg.code.module.bean.CodeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeRecordMapper {
    int countByExample(CodeRecordExample example);

    int deleteByExample(CodeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CodeRecord record);

    int insertSelective(CodeRecord record);

    List<CodeRecord> selectByExample(CodeRecordExample example);

    CodeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CodeRecord record, @Param("example") CodeRecordExample example);

    int updateByExample(@Param("record") CodeRecord record, @Param("example") CodeRecordExample example);

    int updateByPrimaryKeySelective(CodeRecord record);

    int updateByPrimaryKey(CodeRecord record);
}