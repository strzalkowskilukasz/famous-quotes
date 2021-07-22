package com.somecompany.famousquotes.repository;

import com.somecompany.famousquotes.model.Author;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
