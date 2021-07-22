package com.somecompany.famousquotes.controller;

import com.somecompany.famousquotes.dto.AuthorJson;
import com.somecompany.famousquotes.dto.QuoteJson;
import com.somecompany.famousquotes.dto.QuoteMapper;
import com.somecompany.famousquotes.model.Author;
import com.somecompany.famousquotes.model.Quote;
import com.somecompany.famousquotes.service.QuoteService;
import com.somecompany.famousquotes.TestUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.somecompany.famousquotes.TestUtil.APPLICATION_JSON_UTF8;
import static com.somecompany.famousquotes.TestUtil.toJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class QuoteControllerTest {

    @Mock
    private QuoteMapper quoteMapper;

    @Mock
    private QuoteService quoteService;

    private MockMvc mockMvc;

    private Quote quote;

    private Quote newQuote;

    private Quote secondQuote;

    private QuoteJson quoteJson;

    private QuoteJson secondQuoteJson;

    private AuthorJson authorJson;

    @BeforeEach
    void setUp() {
        var quoteModelAssembler = new QuoteModelAssembler();

        var resource = new QuoteController(quoteService, quoteMapper, quoteModelAssembler);

        var author = Author.builder()
                .firstName("Joey")
                .lastName("Tribbiani")
                .id(1L)
                .build();

        authorJson = AuthorJson.builder()
                .firstName("Joey")
                .lastName("Tribbiani")
                .id(1L)
                .build();

        newQuote = Quote.builder()
                .author(author)
                .content("Joey doesn't share food!")
                .build();

        quote = Quote.builder()
                .author(author)
                .content("Joey doesn't share food!")
                .id(1L)
                .build();

        secondQuote = Quote.builder()
                .author(author)
                .content("I don't like people take food from my plate, okay?")
                .id(2L)
                .build();

        quoteJson = QuoteJson.builder()
                .author(authorJson)
                .content("Joey doesn't share food!")
                .id(1L)
                .build();

        secondQuoteJson = QuoteJson.builder()
                .author(authorJson)
                .content("I don't like people take food from my plate, okay?")
                .id(2L)
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(resource).build();
    }

    @Test
    @DisplayName("Should return list of all quotes in json format and status OK")
    void findAll() throws Exception {
        when(quoteService.findAllQuotes()).thenReturn(List.of(quote, secondQuote));

        when(quoteMapper.mapToQuoteJsons(List.of(quote, secondQuote)))
                .thenReturn(List.of(quoteJson, secondQuoteJson));

        mockMvc.perform(get("/api/quotes")
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[*].id")
                        .value(Matchers.containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("content[*].author.firstName")
                        .value(Matchers.containsInAnyOrder("Joey", "Joey")))
                .andExpect(jsonPath("content[*].content")
                        .value(Matchers.containsInAnyOrder(
                                "Joey doesn't share food!",
                                "I don't like people take food from my plate, okay?")));

        verify(quoteService, times(1)).findAllQuotes();
        verify(quoteMapper, times(1))
                .mapToQuoteJsons(List.of(quote, secondQuote));
    }

    @Test
    @DisplayName("Should return one quote in json format and status OK")
    void findById() throws Exception {
        when(quoteService.getById(1L)).thenReturn(quote);

        when(quoteMapper.mapToQuoteJson(quote))
                .thenReturn(quoteJson);

        mockMvc.perform(get("/api/quotes/1")
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("author.firstName").value("Joey"))
                .andExpect(jsonPath("content").value("Joey doesn't share food!"));

        verify(quoteService, times(1)).getById(1L);
        verify(quoteMapper, times(1)).mapToQuoteJson(quote);
    }

    @Test
    @DisplayName("Should add new quote and return in response json with id and status CREATED")
    void add() throws Exception {
        var newQuoteJson = QuoteJson.builder()
                .author(authorJson)
                .content("Joey doesn't share food!")
                .build();

        when(quoteMapper.mapToQuote(newQuoteJson))
                .thenReturn(newQuote);

        when(quoteService.add(newQuote)).thenReturn(quote);

        when(quoteMapper.mapToQuoteJson(quote))
                .thenReturn(quoteJson);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/quotes")
                .contentType(APPLICATION_JSON_UTF8)
                .content(toJsonString(newQuoteJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("author.firstName").value("Joey"))
                .andExpect(jsonPath("content").value("Joey doesn't share food!"));

        verify(quoteService, times(1)).add(newQuote);
        verify(quoteMapper, times(1)).mapToQuote(newQuoteJson);
        verify(quoteMapper, times(1)).mapToQuoteJson(quote);
    }

    @Test
    @DisplayName("Should update existing quote and return in response json with id and status CREATED")
    void update() throws Exception {
        var newQuoteJson = QuoteJson.builder()
                .author(authorJson)
                .content("Joey doesn't share food!")
                .build();

        when(quoteMapper.mapToQuote(newQuoteJson))
                .thenReturn(newQuote);

        when(quoteService.update(newQuote, 1L)).thenReturn(quote);

        when(quoteMapper.mapToQuoteJson(quote))
                .thenReturn(quoteJson);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/quotes/1")
                .contentType(APPLICATION_JSON_UTF8)
                .content(toJsonString(newQuoteJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("author.firstName").value("Joey"))
                .andExpect(jsonPath("content").value("Joey doesn't share food!"));

        verify(quoteService, times(1)).update(newQuote, 1L);
        verify(quoteMapper, times(1)).mapToQuote(newQuoteJson);
        verify(quoteMapper, times(1)).mapToQuoteJson(quote);
    }

    @Test
    @DisplayName("Should delete quote and return status NO_CONTENT")
    void delete() throws Exception {
        doNothing().when(quoteService).deleteById(2L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/quotes/2")
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        verify(quoteService, times(1)).deleteById(2L);
    }
}
