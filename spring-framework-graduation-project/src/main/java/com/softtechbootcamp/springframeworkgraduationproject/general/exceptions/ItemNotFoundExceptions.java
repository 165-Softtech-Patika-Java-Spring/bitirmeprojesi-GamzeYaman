package com.softtechbootcamp.springframeworkgraduationproject.general.exceptions;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.BaseErrorMessage;

public class ItemNotFoundExceptions extends BusinessExceptions{
    public ItemNotFoundExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
