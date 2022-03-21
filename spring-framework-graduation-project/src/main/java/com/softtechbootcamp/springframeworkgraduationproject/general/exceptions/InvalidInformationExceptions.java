package com.softtechbootcamp.springframeworkgraduationproject.general.exceptions;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidInformationExceptions extends BusinessExceptions{
    public InvalidInformationExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
