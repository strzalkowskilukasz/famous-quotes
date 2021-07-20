package com.somecompany.famousqoutes.model;

import com.somecompany.famousqoutes.common.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Table(name = "quote",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"content", "author_id"})})
public class Quote extends BaseEntity {

//  @Lob
  @NotEmpty
  private String content;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;
}
