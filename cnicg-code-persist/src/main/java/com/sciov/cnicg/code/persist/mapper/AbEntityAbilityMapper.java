package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.AbEntityAbility;
import com.sciov.cnicg.code.module.bean.AbEntityAbilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbEntityAbilityMapper {
    int countByExample(AbEntityAbilityExample example);

    int deleteByExample(AbEntityAbilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AbEntityAbility record);

    int insertSelective(AbEntityAbility record);

    List<AbEntityAbility> selectByExample(AbEntityAbilityExample example);

    AbEntityAbility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AbEntityAbility record, @Param("example") AbEntityAbilityExample example);

    int updateByExample(@Param("record") AbEntityAbility record, @Param("example") AbEntityAbilityExample example);

    int updateByPrimaryKeySelective(AbEntityAbility record);

    int updateByPrimaryKey(AbEntityAbility record);
}