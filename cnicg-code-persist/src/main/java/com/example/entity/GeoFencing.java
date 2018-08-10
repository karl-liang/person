package com.example.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.example.entity.GeoFencing")
@Table(name = "geo_fencing")
public class GeoFencing {

  /**
   * 地理围栏
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"")
  private Integer id;
  /**
   * 地理code
   */
  @Column(name = "\"adcode\"")
  private String adcode;
  /**
   * 围栏GID
   */
  @Column(name = "\"fence_gid\"")
  private String fenceGid;
}