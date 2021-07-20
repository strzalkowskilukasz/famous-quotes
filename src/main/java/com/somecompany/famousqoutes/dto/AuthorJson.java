package com.somecompany.famousqoutes.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class AuthorJson {

  private Long id;

  private String firstName;

  @NotEmpty
  private String lastName;
}
