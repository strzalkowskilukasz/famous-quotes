package com.somecompany.famousquotes.repository;

import com.somecompany.famousquotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

  List<Quote> findAllByAuthorId(Long id);
}
