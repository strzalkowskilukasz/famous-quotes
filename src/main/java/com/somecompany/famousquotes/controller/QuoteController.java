package com.somecompany.famousquotes.controller;

import com.somecompany.famousquotes.dto.QuoteJson;
import com.somecompany.famousquotes.dto.QuoteMapper;
import com.somecompany.famousquotes.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
public class QuoteController {

  private final QuoteService quoteService;

  private final QuoteMapper quoteMapper;

  private final QuoteModelAssembler assembler;

  @GetMapping
  public CollectionModel<EntityModel<QuoteJson>> findAll() {
    var quotes = quoteService.findAllQuotes();

    List<EntityModel<QuoteJson>> quotesJson = quoteMapper.mapToQuoteJsons(quotes).stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(quotesJson,
        linkTo(methodOn(QuoteController.class).findAll()).withSelfRel());
  }

  @GetMapping("/{id}")
  public EntityModel<QuoteJson> findById(@PathVariable Long id) {
    var quote = quoteService.getById(id);

    var quotesJson = quoteMapper.mapToQuoteJson(quote);

    return assembler.toModel(quotesJson);
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody @Valid QuoteJson newQuoteJson) {
    var newQuote = quoteMapper.mapToQuote(newQuoteJson);

    var quoteJson = quoteMapper.mapToQuoteJson(quoteService.add(newQuote));

    return getResponseEntity(quoteJson);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody @Valid QuoteJson newQuoteJson,
                                  @PathVariable Long id) {
    var newQuote = quoteMapper.mapToQuote(newQuoteJson);

    var quoteJson = quoteMapper.mapToQuoteJson(quoteService.update(newQuote, id));

    return getResponseEntity(quoteJson);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(@PathVariable Long id) {

    quoteService.deleteById(id);

    return ResponseEntity.noContent().build();
  }

  private ResponseEntity<?> getResponseEntity(QuoteJson quoteJson) {
    var quoteEntityModel = assembler.toModel(quoteJson);

    return ResponseEntity
        .created(quoteEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(quoteEntityModel);
  }
}
