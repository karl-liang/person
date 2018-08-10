package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.Code")
@Table(name = "code")
public class Code {

  /**
   * 码
   */
  @Id
  @Column(name = "\"id\"")
  private Integer id;
  @Column(name = "\"code\"")
  private String code;
  @Column(name = "\"state\"")
  private String state;
}