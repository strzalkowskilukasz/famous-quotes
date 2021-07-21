package com.somecompany.famousquotes.service;

import com.somecompany.famousqoutes.repository.QuoteRepository;
import com.somecompany.famousqoutes.service.AuthorService;
import com.somecompany.famousqoutes.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    private QuoteService quoteService;

    private AuthorService authorService;

    @Mock
    private QuoteRepository quoteRepository;

    @BeforeEach
    void setup() {
        quoteService = new QuoteService(quoteRepository, authorService);
    }
}
