package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.AbEntityClassEvent")
@Table(name = "ab_entity_class_event")
public class AbEntityClassEvent {

  /**
   * 课程事件
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 课程ID
   */
  @Column(name = "\"class_id\"")
  private Integer classId;
  /**
   * 实例ID
   */
  @Column(name = "\"entity_id\"")
  private Integer entityId;
  /**
   * 发生时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
  /**
   * 结束时间
   */
  @Column(name = "\"gmt_end\"")
  private Timestamp gmtEnd;
  /**
   * 分数
   */
  @Column(name = "\"score\"")
  private Integer score;
}