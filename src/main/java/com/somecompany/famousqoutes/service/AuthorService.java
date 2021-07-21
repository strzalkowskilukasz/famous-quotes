package com.somecompany.famousqoutes.service;

import com.somecompany.famousqoutes.model.Author;
import com.somecompany.famousqoutes.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Synchronized
  public Author findOrSaveNewAuthor(Author author) {
    return authorRepository
        .findByFirstNameAndLastName(author.getFirstName().toUpperCase(),
            author.getLastName().toUpperCase())
        .orElseGet(() -> save(author));
  }

  public void deleteById(Long id) {
    authorRepository.deleteById(id);
  }

  private Author save(Author author) {

    author.setFirstName(author.getFirstName().toUpperCase());
    author.setLastName(author.getLastName().toUpperCase());

    return authorRepository.save(author);
  }
}
