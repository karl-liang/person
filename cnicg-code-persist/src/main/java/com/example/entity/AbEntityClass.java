package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.AbEntityClass")
@Table(name = "ab_entity_class")
public class AbEntityClass {

  /**
   * 能力课程
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 课程名称
   */
  @Column(name = "\"class_name\"")
  private String className;
  /**
   * 课程状态
   */
  @Column(name = "\"class_status\"")
  private Integer classStatus;
  /**
   * 目标能力值
   */
  @Column(name = "\"ability_id\"")
  private Integer abilityId;
  /**
   * 课程目标
   */
  @Column(name = "\"target_score\"")
  private Integer targetScore;
  /**
   * 当前分数
   */
  @Column(name = "\"current_score\"")
  private Integer currentScore;
  /**
   * 创建时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
  /**
   * 计划完成时间
   */
  @Column(name = "\"gmt_plan_finish\"")
  private Timestamp gmtPlanFinish;
  /**
   * 实际结束时间
   */
  @Column(name = "\"gmt_end\"")
  private Timestamp gmtEnd;
  /**
   * 实体ID
   */
  @Column(name = "\"entity_id\"")
  private Integer entityId;
}