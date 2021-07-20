package com.somecompany.famousqoutes.dto;

import com.somecompany.famousqoutes.model.Quote;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuoteMapper {

  Quote mapToQuote(QuoteJson quoteJson);

  QuoteJson mapToQuoteJson(Quote quote);

  List<QuoteJson> mapToQuoteJsons(List<Quote> quotes);
}
