package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.District")
@Table(name = "district")
public class District {

  /**
   * 地址围栏
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 地区名称
   */
  @Column(name = "\"name\"")
  private String name;
  /**
   * 城市代码
   */
  @Column(name = "\"city_code\"")
  private String cityCode;
  /**
   * 边界
   */
  @Column(name = "\"poly_line\"")
  private String polyLine;
  /**
   * 中心
   */
  @Column(name = "\"center\"")
  private String center;
  /**
   * 地址级别
   */
  @Column(name = "\"level\"")
  private String level;
  /**
   * 区的adcode
   */
  @Column(name = "\"adcode\"")
  private String adcode;
  /**
   * 父
   */
  @Column(name = "\"parent_id\"")
  private Integer parentId;
}