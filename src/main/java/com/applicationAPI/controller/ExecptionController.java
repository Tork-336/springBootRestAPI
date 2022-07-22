package com.applicationAPI.controller;

import com.applicationAPI.execption.BusinessLogicException;
import com.applicationAPI.execption.ModelNotFoundExecption;
import com.applicationAPI.repository.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ExecptionController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ExceptionDto handlerExceptionHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest webRequest) {
        if (Objects.isNull(exception.getHttpInputMessage())) {
            return new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Debe enviar un body/pay-load.", exception.getMessage());
        } else {
            return new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "El Json no tiene la estructura adecuada.", exception.getLocalizedMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionDto handlervalidationException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), errors.toString(), exception.getMessage());
    }

    @ExceptionHandler(ModelNotFoundExecption.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionDto handlerExecption(Exception exception, WebRequest webRequest) {
        return new ExceptionDto(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Error con Exeception handler.", "Error interno del servidor, se perdio conexion con BD.");
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionDto handlerExceptionBusinessLogic() {
        return new ExceptionDto(LocalDateTime.now(), HttpStatus.SERVICE_UNAVAILABLE.value(), "Error con Exeception handler.", "Error interno del servidor, se perdio conexion con BD.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ExceptionDto handlerExceptionReuestParam(MissingServletRequestParameterException exception, WebRequest webRequest) {
        return new ExceptionDto(LocalDateTime.now(), HttpStatus.PAYMENT_REQUIRED.value(), exception.getParameterName() + ":" + exception.getParameterType(), exception.getMessage());
    }
}
