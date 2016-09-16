package com.db.retailmanager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * @author ravvenkatara
 * This is the global error handler. Any Error/Exception will be handled by this class.
 *
 */
@ControllerAdvice
public class DefaultGlobalErrorHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<InternalError> handleException(Exception exception) {
        return buildExceptionResource(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({ BaseException.class })
    public ResponseEntity<String> handleError(BaseException exception) {
        return buildErrorResource(exception, null);
    }
    
    private ResponseEntity<InternalError> buildExceptionResource(Throwable throwable, HttpStatus httpStatus) {
        
    	return new ResponseEntity<InternalError>(new InternalError(throwable.getMessage()), httpStatus);
    }
    
    private ResponseEntity<String> buildErrorResource(BaseException exception, HttpStatus httpStatus) {
        if(exception instanceof RetailManagerValidationError)
        {
        	return new ResponseEntity<String>(exception.getMessageDetail(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<String>(exception.getMessageDetail(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}