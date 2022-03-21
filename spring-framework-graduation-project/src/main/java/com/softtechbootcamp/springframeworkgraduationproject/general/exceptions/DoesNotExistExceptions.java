package com.softtechbootcamp.springframeworkgraduationproject.general.exceptions;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DoesNotExistExceptions extends BusinessExceptions{
    public DoesNotExistExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

}
