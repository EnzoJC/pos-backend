package pe.edu.upn.pos.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.edu.upn.pos.dto.response.ApiErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse<>(HttpStatus.OK, List.of(ex.getMessage())), HttpStatus.OK);
    }

    @ExceptionHandler(ValueRepeatedException.class)
    public ResponseEntity<ApiErrorResponse> handleValueRepeatedException(ValueRepeatedException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse<>(HttpStatus.OK, List.of(ex.getMessage())), HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse<>(HttpStatus.UNAUTHORIZED, List.of("The username or password you’ve entered is incorrect")), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(GlobalDateFormatException.class)
    public ResponseEntity<ApiErrorResponse> handleDateFormatException(GlobalDateFormatException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse<>(HttpStatus.OK, List.of(ex.getMessage())), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ApiErrorResponse<>(status, errors), HttpStatus.BAD_REQUEST);
    }
}
