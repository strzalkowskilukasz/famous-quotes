package com.somecompany.famousquotes.model;

import com.somecompany.famousquotes.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Author extends BaseEntity {

    private String firstName;

    @NotNull
    private String lastName;
}
