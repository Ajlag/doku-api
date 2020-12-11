package org.i4di.common.api.advice;

import org.i4di.common.dto.APIErrorDTO;
import org.i4di.common.dto.FieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class APIErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIErrorDTO processValidationError(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ObjectError> objectErrors = result.getGlobalErrors();

        return processFieldErrors(fieldErrors, objectErrors);
    }

    private APIErrorDTO processFieldErrors(List<FieldError> fieldErrors, List<ObjectError> objectErrors) {
        APIErrorDTO response = new APIErrorDTO();

        for (FieldError error : fieldErrors) {
            response.addFieldError(error.getField(), error.getDefaultMessage());
        }

        for (ObjectError error : objectErrors) {
            response.addFieldError(error.getObjectName().replace("DTO", ""), error.getDefaultMessage());
        }

        return response;
    }
}
