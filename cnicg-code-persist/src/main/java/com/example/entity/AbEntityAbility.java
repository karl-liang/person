package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.AbEntityAbility")
@Table(name = "ab_entity_ability")
public class AbEntityAbility {

  /**
   * 实体能力
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 能力名称
   */
  @Column(name = "\"ability_name\"")
  private String abilityName;
  /**
   * 能力值
   */
  @Column(name = "\"ability_score\"")
  private Integer abilityScore;
  /**
   * 能力值状态
   */
  @Column(name = "\"ability_status\"")
  private Integer abilityStatus;
  /**
   * 创建时间
   */
  @Column(name = "\"gmt_create\"")
  private Timestamp gmtCreate;
}