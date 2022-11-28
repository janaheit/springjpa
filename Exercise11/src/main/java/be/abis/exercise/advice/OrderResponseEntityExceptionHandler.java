package be.abis.exercise.advice;

import be.abis.exercise.error.ApiError;
import be.abis.exercise.error.ValidationError;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class OrderResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<? extends Object> handlePersonNotFound(PersonNotFoundException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("person not found", status.value(), ex.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(PersonCanNotBeDeletedException.class)
    public ResponseEntity<? extends Object> handlePersonCanNotBeDeletedException(PersonCanNotBeDeletedException ex, WebRequest request){

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("person cannot be deleted", status.value(), ex.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<? extends Object> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError ae = new ApiError("person already exists", status.value(), ex.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(ae, responseHeaders, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        ApiError ae = new ApiError("invalid arguments", status.value(), ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> validationErrors = ae.getInvalidParams();
        for (FieldError fe:fieldErrors){
            ValidationError validationError = new ValidationError();
            validationError.setName(fe.getField());
            validationError.setReason(fe.getDefaultMessage());
            validationErrors.add(validationError);
        }

        headers.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<>(ae, headers, status);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                                 WebRequest request){
        ApiError ae = new ApiError("type conversion failed", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<ApiError>(ae, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
