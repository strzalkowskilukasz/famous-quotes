package com.somecompany.famousqoutes.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class QuoteJson {

  private Long id;

  @NotEmpty(message = "Treść cytatu nie może być pusta")
  private String content;

  @Valid
  @NotNull(message = "Cytat musi posiadać autora")
  private AuthorJson author;
}
