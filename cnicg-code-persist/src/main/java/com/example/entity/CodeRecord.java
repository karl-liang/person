package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.CodeRecord")
@Table(name = "code_record")
public class CodeRecord {

  /**
   * 扫码记录
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  @Column(name = "\"code\"")
  private String code;
  /**
   * 当前状态
   */
  @Column(name = "\"state\"")
  private String state;
  /**
   * 进入时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
  /**
   * 状态变更地点
   */
  @Column(name = "\"location\"")
  private String location;
  /**
   * 相关事件
   */
  @Column(name = "\"relate_event\"")
  private String relateEvent;
}