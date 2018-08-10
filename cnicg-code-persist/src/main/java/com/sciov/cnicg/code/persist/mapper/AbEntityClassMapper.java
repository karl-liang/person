package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.AbEntityClass;
import com.sciov.cnicg.code.module.bean.AbEntityClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbEntityClassMapper {
    int countByExample(AbEntityClassExample example);

    int deleteByExample(AbEntityClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AbEntityClass record);

    int insertSelective(AbEntityClass record);

    List<AbEntityClass> selectByExample(AbEntityClassExample example);

    AbEntityClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AbEntityClass record, @Param("example") AbEntityClassExample example);

    int updateByExample(@Param("record") AbEntityClass record, @Param("example") AbEntityClassExample example);

    int updateByPrimaryKeySelective(AbEntityClass record);

    int updateByPrimaryKey(AbEntityClass record);
}