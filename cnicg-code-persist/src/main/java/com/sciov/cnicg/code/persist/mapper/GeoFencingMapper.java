package com.sciov.cnicg.code.persist.mapper;

import com.sciov.cnicg.code.module.bean.GeoFencing;
import com.sciov.cnicg.code.module.bean.GeoFencingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GeoFencingMapper {
    int countByExample(GeoFencingExample example);

    int deleteByExample(GeoFencingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GeoFencing record);

    int insertSelective(GeoFencing record);

    List<GeoFencing> selectByExample(GeoFencingExample example);

    GeoFencing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GeoFencing record, @Param("example") GeoFencingExample example);

    int updateByExample(@Param("record") GeoFencing record, @Param("example") GeoFencingExample example);

    int updateByPrimaryKeySelective(GeoFencing record);

    int updateByPrimaryKey(GeoFencing record);
}