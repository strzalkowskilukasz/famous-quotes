package com.somecompany.famousqoutes.repository;

import com.somecompany.famousqoutes.model.Quote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

  List<Quote> findAllByAuthorId(Long id);
}
