package com.somecompany.famousqoutes.controller;

import com.somecompany.famousqoutes.dto.QuoteJson;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuoteModelAssembler implements RepresentationModelAssembler<QuoteJson,
    EntityModel<QuoteJson>> {

  @Override
  public EntityModel<QuoteJson> toModel(QuoteJson quoteJson) {
    return new EntityModel<>(quoteJson,
        linkTo(methodOn(QuoteController.class).findById(quoteJson.getId())).withSelfRel(),
        linkTo(methodOn(QuoteController.class).findAll()).withRel("quotes"));
  }
}
