package com.softtechbootcamp.springframeworkgraduationproject.general.exceptions;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateException extends BusinessExceptions{
    public DuplicateException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
