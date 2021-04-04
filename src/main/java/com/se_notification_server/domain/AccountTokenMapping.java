package com.se_notification_server.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class AccountTokenMapping {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountTagMappingId;

  @Column
  private Long accountId;

  @Column
  @Size(min = 10, max = 255)
  private String token;

  public Long getAccountId() {
    return accountId;
  }

  public String getToken() {
    return token;
  }

}
