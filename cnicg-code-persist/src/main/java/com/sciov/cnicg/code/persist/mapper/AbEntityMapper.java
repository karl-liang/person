package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.AbEntity;
import com.sciov.cnicg.code.module.bean.AbEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbEntityMapper {
    int countByExample(AbEntityExample example);

    int deleteByExample(AbEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AbEntity record);

    int insertSelective(AbEntity record);

    List<AbEntity> selectByExample(AbEntityExample example);

    AbEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AbEntity record, @Param("example") AbEntityExample example);

    int updateByExample(@Param("record") AbEntity record, @Param("example") AbEntityExample example);

    int updateByPrimaryKeySelective(AbEntity record);

    int updateByPrimaryKey(AbEntity record);
}