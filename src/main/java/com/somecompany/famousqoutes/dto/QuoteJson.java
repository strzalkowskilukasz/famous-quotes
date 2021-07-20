package com.somecompany.famousqoutes.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

  @NotEmpty
  private String content;

  @Valid
  private AuthorJson author;
}
