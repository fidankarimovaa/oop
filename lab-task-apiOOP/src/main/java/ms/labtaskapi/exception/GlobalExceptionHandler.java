package ms.labtaskapi.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(NotFoundException ex,
                                                      WebRequest request) {
        log.trace("Application exception occurred", ex);
        return ofType(request, HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

//    validationlar uygun gelmedikde
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex,
                                                      WebRequest request) {
        log.trace("Application exception occurred", ex);
        final List<ConstraintsViolationError> list = ex.getFieldErrors()
                .stream()
                .map(fieldError -> new ConstraintsViolationError(fieldError.getField(), fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()))
                .toList();


        return ofType(request, HttpStatus.NOT_FOUND, "Validation failed for argument", list);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message, List<ConstraintsViolationError> list) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put("status", status.value());
        attributes.put("message", message);
        attributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        attributes.put("fieldErrors", list);
        return new ResponseEntity<>(attributes, status);
    }

}

