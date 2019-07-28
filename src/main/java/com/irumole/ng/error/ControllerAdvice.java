package com.irumole.ng.error;

import com.irumole.ng.dto.GenericServiceResponse;
import com.irumole.ng.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BankNotFoundExecption.class)
    public final ResponseEntity<GenericServiceResponse> bankNotFoundException(BankNotFoundExecption ex, WebRequest request) {
        String errorMessage = "No Bank found for " + ex.getMessage();
        return new ResponseEntity<>(getGenericResponse(errorMessage, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<GenericServiceResponse> customerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage() + " not found.";
        return new ResponseEntity<>(getGenericResponse(errorMessage, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingCustomerException.class)
    public final ResponseEntity<GenericServiceResponse> existingCustomerException(ExistingCustomerException ex, WebRequest request) {
        String errorMessage = ex.getMessage() + " already exist.";
        return new ResponseEntity<>(getGenericResponse(errorMessage, request), HttpStatus.CONFLICT);
    }

    private GenericServiceResponse getGenericResponse(String errorMessage, WebRequest request){
        return new GenericServiceResponse(Status.FAILED, errorMessage, request.getDescription(false));
    }
}
