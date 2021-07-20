package com.somecompany.famousqoutes.model;

import com.somecompany.famousqoutes.common.BaseEntity;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Author extends BaseEntity {

  private String firstName;

  @NotEmpty
  private String lastName;
}
