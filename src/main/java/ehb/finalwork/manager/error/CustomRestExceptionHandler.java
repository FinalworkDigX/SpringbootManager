package ehb.finalwork.manager.error;

import com.auth0.exception.Auth0Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());
    private SimpMessagingTemplate webSocket;

    public CustomRestExceptionHandler(SimpMessagingTemplate simpMessagingTemplate) {
        webSocket = simpMessagingTemplate;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Custom Exceptions
    @ExceptionHandler(value = TooManyReturnValuesException.class)
    public ResponseEntity<Object> handleTooManyReturnValuesException(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.MULTIPLE_CHOICES, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = CustomNotFoundException.class)
    public ResponseEntity<Object> handleCustomNotFoundException(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Object> handleLoginException(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = SignupException.class)
    public ResponseEntity<Object> handleSignupException(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = Auth0Exception.class)
    public ResponseEntity<Object> handleAuth0Exception(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.SEE_OTHER, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Custom WebSocket Exceptions
    @MessageExceptionHandler(value = TooManyReturnValuesWebSocketException.class)
    public void handleTooManyReturnValuesWebSocketException(TooManyReturnValuesWebSocketException exception) {
        ApiError apiError = new ApiError(HttpStatus.MULTIPLE_CHOICES, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        webSocket.convertAndSend("/topic/" + exception.getChannel(), apiError);
    }

    @MessageExceptionHandler(value = CustomNotFoundWebSocketException.class)
    public void handleCustomNotFoundWebSocketException(CustomNotFoundWebSocketException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), (exception.getMessage() == null)? exception.getMessage():"");
        webSocket.convertAndSend("/topic/" + exception.getChannel(), apiError);
    }
}
