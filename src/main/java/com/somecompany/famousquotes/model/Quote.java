package com.somecompany.famousquotes.model;

import com.somecompany.famousquotes.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "quote",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"content", "author_id"})})
public class Quote extends BaseEntity {

  @NotEmpty
  private String content;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;
}
