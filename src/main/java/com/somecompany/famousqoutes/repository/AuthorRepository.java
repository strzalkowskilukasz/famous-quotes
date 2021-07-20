package com.somecompany.famousqoutes.repository;

import com.somecompany.famousqoutes.model.Author;
import com.somecompany.famousqoutes.model.Quote;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
