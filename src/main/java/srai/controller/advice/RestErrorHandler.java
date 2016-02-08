package srai.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** RestController error handler. */
@ControllerAdvice
public class RestErrorHandler {

  /** Class logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

  /** message source. */
  @Autowired
  private transient MessageSource messageSource;

  /** Validation failure error handler. */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> processValidationError(
      final MethodArgumentNotValidException ex) {
    LOGGER.debug("Handling validation errors");

    final BindingResult result = ex.getBindingResult();
    final List<FieldError> fieldErrors = result.getFieldErrors();

    return processFieldErrors(fieldErrors);
  }

  private Map<String, String> processFieldErrors(final List<FieldError> fieldErrors) {
    final Map<String, String> errors = new ConcurrentHashMap<String, String>();
    for (final FieldError fieldError : fieldErrors) {
      final String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
      errors.put(fieldError.getField(), localizedErrorMessage);
      //                    fieldError.getDefaultMessage());
    }

    return errors;
  }

  private String resolveLocalizedErrorMessage(final FieldError fieldError) {
    final Locale currentLocale = LocaleContextHolder.getLocale();
    String localizedErrorMessage = messageSource.getMessage(fieldError,
        currentLocale);

    // If a message was not found, return the most accurate field error code
    // instead.
    // You can remove this check if you prefer to get the default error
    // message.
    if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
      final String[] fieldErrorCodes = fieldError.getCodes();
      localizedErrorMessage = fieldErrorCodes[0];
    }

    return localizedErrorMessage;
  }
}