package app.tca.thecloutapp.exceptions;


import app.tca.thecloutapp.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
