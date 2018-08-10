package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.AbEntityClassEvent;
import com.sciov.cnicg.code.module.bean.AbEntityClassEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbEntityClassEventMapper {
    int countByExample(AbEntityClassEventExample example);

    int deleteByExample(AbEntityClassEventExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AbEntityClassEvent record);

    int insertSelective(AbEntityClassEvent record);

    List<AbEntityClassEvent> selectByExample(AbEntityClassEventExample example);

    AbEntityClassEvent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AbEntityClassEvent record, @Param("example") AbEntityClassEventExample example);

    int updateByExample(@Param("record") AbEntityClassEvent record, @Param("example") AbEntityClassEventExample example);

    int updateByPrimaryKeySelective(AbEntityClassEvent record);

    int updateByPrimaryKey(AbEntityClassEvent record);
}