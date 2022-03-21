package com.softtechbootcamp.springframeworkgraduationproject.general.exception;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final  ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse =  new GenExceptionResponse(errorDate, message, description);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDoesNotExistExceptions(DoesNotExistExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detailMessage);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateExceptions(DuplicateException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detailMessage);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidInformationExceptions(InvalidInformationExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detailMessage);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllItemNotFoundException(ItemNotFoundExceptions ex){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detailMessage);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundExceptions ex){
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse();

        Date errorDate = new Date();

        if (ex.getGenericErrorMessage() != null){
            genExceptionResponse.setMessage(ex.getGenericErrorMessage());
            genExceptionResponse.setErrorDate(errorDate);
        }else{
            genExceptionResponse.setErrorDate(errorDate);
            genExceptionResponse.setMessage(ex.getBaseErrorMessage().getMessage());
            genExceptionResponse.setDetailMessage(ex.getBaseErrorMessage().getDetailMessage());
        }

        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        if (ex.getGenericErrorMessage() == null){
            generalResponse.setMessage(ex.getBaseErrorMessage().getDetailMessage());
        }else{
            generalResponse.setMessage(genExceptionResponse.getMessage());
        }

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Date errorDate = new Date();
        String message =  new StringBuilder(ex.getBindingResult().getFieldError().getField()).append(" field not valid.").toString();
        String detailMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detailMessage);
        RestResponse<GenExceptionResponse> generalResponse = RestResponse.error(genExceptionResponse);
        generalResponse.setMessage(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.BAD_REQUEST);
    }

}
