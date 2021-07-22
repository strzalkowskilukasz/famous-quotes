package com.somecompany.famousquotes.repository;

import com.somecompany.famousquotes.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.somecompany.famousquotes.TestUtil.persistAll;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AuthorRepositoryIT {

    @Autowired
    AuthorRepository authorRepository;

    @PersistenceContext
    EntityManager entityManager;

    private Author author;

    private Author secondAuthor;

    @BeforeEach
    void setUp() {
        author = Author.builder()
                .firstName("Joey")
                .lastName("Tribbiani")
                .build();

        secondAuthor = Author.builder()
                .firstName("Chandler")
                .lastName("Bing")
                .build();

        persistAll(
                entityManager,
                author,
                secondAuthor
        );
    }

    @Test
    @Named("Should find author by firstName and lastName")
    void findByFirstNameAndLastName() {
        var firstActual = authorRepository.findByFirstNameAndLastName("Joey", "Tribbiani");
        var secondActual = authorRepository.findByFirstNameAndLastName("Chandler", "Bing");
        var thirdActual = authorRepository.findByFirstNameAndLastName("CHANDLER", "BiNg");

        assertThat(firstActual).isPresent();
        firstActual.ifPresent(actual -> assertThat(actual).usingRecursiveComparison().isEqualTo(author));

        assertThat(secondActual).isPresent();
        secondActual.ifPresent(actual -> assertThat(actual).usingRecursiveComparison().isEqualTo(secondAuthor));

        assertThat(thirdActual).isNotPresent();
    }
}
