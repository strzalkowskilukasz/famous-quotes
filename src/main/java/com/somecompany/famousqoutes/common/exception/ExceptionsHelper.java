package com.somecompany.famousqoutes.common.exception;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class ExceptionsHelper {

    private final String MAIN_MESSAGE_PART = "Wystąpiły błędy walidacji: ";

    public ErrorResponse getValidationErrorResponse(MethodArgumentNotValidException exception,
                                                    HttpServletRequest request,
                                                    HttpStatus status) {

        var message = exception.getBindingResult()
            .getFieldErrors().stream()
            .map(mapFieldError())
            .sorted(Comparator.comparing(ErrorMessage::getName))
            .map(getErrorMessage())
            .collect(Collectors.joining(", ", MAIN_MESSAGE_PART, "!"));

        return ErrorResponse.builder()
            .status(status.value())
            .error(exception.getClass().getSimpleName())
            .message(message)
            .url(request.getRequestURI())
            .build();
    }

    private Function<ErrorMessage, String> getErrorMessage() {
        return errorMessage -> String.join(": ", "("
            + errorMessage.getName() + ")", errorMessage.getMessage());
    }

    private Function<FieldError, ErrorMessage> mapFieldError() {
        return fieldError -> ErrorMessage.builder()
            .name(fieldError.getField())
            .message(fieldError.getDefaultMessage())
            .build();
    }
}
