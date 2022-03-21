package com.softtechbootcamp.springframeworkgraduationproject.general.exceptions;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptions extends BusinessExceptions {
    private String genericErrorMessage;

    public EntityNotFoundExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

    public String getGenericErrorMessage() {
        return genericErrorMessage;
    }

    public EntityNotFoundExceptions(String genericErrorMessage) {
        this.genericErrorMessage = genericErrorMessage;
    }
}
