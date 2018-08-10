package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.Trail")
@Table(name = "trail")
public class Trail {

  /**
   * 位置跟踪
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 跟踪主体
   */
  @Column(name = "\"person\"")
  private String person;
  /**
   * 上报的经纬度
   */
  @Column(name = "\"point\"")
  private String point;
  /**
   * 解析的可读地址
   */
  @Column(name = "\"location\"")
  private String location;
  /**
   * 上报时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
}