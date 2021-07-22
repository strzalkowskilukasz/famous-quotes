package com.somecompany.famousquotes.dto;

import com.somecompany.famousquotes.model.Author;
import com.somecompany.famousquotes.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Named;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuoteMapperTest {

    private QuoteMapper quoteMapper;

    private Quote quote;

    private Quote secondQuote;

    private QuoteJson quoteJson;

    private QuoteJson secondQuoteJson;

    @BeforeEach
    void setUp() {
        quoteMapper = new QuoteMapperImpl();

        var author = Author.builder()
                .id(1L)
                .firstName("Joey")
                .lastName("Tribbiani")
                .build();

        var secondAuthor = Author.builder()
                .id(2L)
                .firstName("Chandler")
                .lastName("Bing")
                .build();

        var authorJson = AuthorJson.builder()
                .id(1L)
                .firstName("Joey")
                .lastName("Tribbiani")
                .build();

        var secondAuthorJson = AuthorJson.builder()
                .id(2L)
                .firstName("Chandler")
                .lastName("Bing")
                .build();

        quote = Quote.builder()
                .id(1L)
                .author(author)
                .content("Joey doesn't share food!")
                .build();

        secondQuote = Quote.builder()
                .id(2L)
                .author(secondAuthor)
                .content("I don't like people take food from my plate, okay?")
                .build();

        quoteJson = QuoteJson.builder()
                .id(1L)
                .author(authorJson)
                .content("Joey doesn't share food!")
                .build();

        secondQuoteJson = QuoteJson.builder()
                .id(2L)
                .author(secondAuthorJson)
                .content("I don't like people take food from my plate, okay?")
                .build();
    }

    @Test
    @Named("Should map QuoteJson to Quote")
    void mapToQuote() {
        assertThat(quoteMapper.mapToQuote(quoteJson)).usingRecursiveComparison().isEqualTo(quote);
    }

    @Test
    @Named("Should map Quote to QuoteJson")
    void mapToQuoteJson() {
        assertThat(quoteMapper.mapToQuoteJson(quote)).usingRecursiveComparison().isEqualTo(quoteJson);
    }

    @Test
    @Named("Should map list of quotes to list of quoteJsons")
    void mapToQuoteJsons() {
        assertThat(quoteMapper.mapToQuoteJsons(List.of(quote, secondQuote)))
                .usingRecursiveComparison()
                .isEqualTo(List.of(quoteJson, secondQuoteJson));
    }
}
