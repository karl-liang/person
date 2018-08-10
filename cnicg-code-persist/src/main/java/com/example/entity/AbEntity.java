package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.AbEntity")
@Table(name = "ab_entity")
public class AbEntity {

  /**
   * 能力实体
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 姓名
   */
  @Column(name = "\"name\"")
  private String name;
  /**
   * 年龄
   */
  @Column(name = "\"age\"")
  private Integer age;
  /**
   * 创建时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
  /**
   * 实体状态
   */
  @Column(name = "\"entity_status\"")
  private Integer entityStatus;
  /**
   * 更新时间
   */
  @Column(name = "\"gmt_update\"")
  private Timestamp gmtUpdate;
  /**
   * 生日
   */
  @Column(name = "\"gmt_birthday\"")
  private Date gmtBirthday;
}