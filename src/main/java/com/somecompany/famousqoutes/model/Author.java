package com.somecompany.famousqoutes.model;

import com.somecompany.famousqoutes.common.BaseEntity;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Author extends BaseEntity {

  private String firstName;

  @NotEmpty
  private String lastName;
}
