package com.somecompany.famousquotes.repository;

import com.somecompany.famousquotes.model.Author;
import com.somecompany.famousquotes.model.Quote;
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
public class QuoteRepositoryIT {

    @Autowired
    QuoteRepository quoteRepository;

    @PersistenceContext
    EntityManager entityManager;

    private Quote quote;

    private Quote secondQuote;

    private Quote thirdQuote;

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

        quote = Quote.builder()
                .author(author)
                .content("Joey doesn't share food!")
                .build();

        secondQuote = Quote.builder()
                .author(author)
                .content("I don't like people take food from my plate, okay?")
                .build();

        thirdQuote = Quote.builder()
                .author(secondAuthor)
                .content("Hi I'm Chandler. I make jokes when I'm uncomfortable.")
                .build();

        persistAll(
                entityManager,
                author,
                secondAuthor,
                quote,
                secondQuote,
                thirdQuote
        );
    }

    @Test
    @Named("Should find only these quotes with provided author id")
    void findAllByAuthorId() {
        var firstActual = quoteRepository.findAllByAuthorId(author.getId());
        var secondActual = quoteRepository.findAllByAuthorId(secondAuthor.getId());
        var thirdActual = quoteRepository.findAllByAuthorId(Long.MAX_VALUE);

        assertThat(firstActual).hasSize(2);
        assertThat(firstActual).containsExactlyInAnyOrder(quote, secondQuote);

        assertThat(secondActual).hasSize(1);
        assertThat(secondActual).containsExactlyInAnyOrder(thirdQuote);

        assertThat(thirdActual).hasSize(0);
    }
}
