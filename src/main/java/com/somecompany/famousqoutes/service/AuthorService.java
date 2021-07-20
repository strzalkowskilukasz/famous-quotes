package com.somecompany.famousqoutes.service;

import com.somecompany.famousqoutes.model.Author;
import com.somecompany.famousqoutes.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public Author findOrSaveNewAuthor(Author author) {
    return authorRepository
        .findByFirstNameAndLastName(author.getFirstName(), author.getLastName())
        .orElseGet(() -> save(author));
  }

  private Author save(Author author) {

    var newAuthor = Author.builder()
        .firstName(author.getFirstName())
        .lastName(author.getLastName())
        .build();

    return authorRepository.save(newAuthor);
  }
}
