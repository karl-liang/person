package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.Trail;
import com.sciov.cnicg.code.module.bean.TrailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrailMapper {
    int countByExample(TrailExample example);

    int deleteByExample(TrailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Trail record);

    int insertSelective(Trail record);

    List<Trail> selectByExample(TrailExample example);

    Trail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Trail record, @Param("example") TrailExample example);

    int updateByExample(@Param("record") Trail record, @Param("example") TrailExample example);

    int updateByPrimaryKeySelective(Trail record);

    int updateByPrimaryKey(Trail record);
}