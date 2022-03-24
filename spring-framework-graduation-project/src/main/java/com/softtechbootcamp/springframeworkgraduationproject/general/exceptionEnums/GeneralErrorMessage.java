package com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums;

public enum GeneralErrorMessage implements BaseErrorMessage{

    ENTITY_NOT_FOUND("Item not found!", "Item cannot find with this id."),
    INVALID_REQUEST("Invalid parameter", "The request sent with parameters is  incorrect."),
    INTERNAL_SERVER_ERROR("Encounter internal server", "Server encountered an unexpected condition that prevented it from fulfilling the request"),
    PRODUCT_NOT_FOUND("Product not found!", "Product cannot found with this id"),
    PRODUCT_TYPE_NOT_FOUND("Product type not found!", "Product type cannot found with this id"),
    PRODUCT_TYPE_ERROR("Cannot be same product type name! ", "There is already a type with same name!"),
    USER_NOT_FOUND("User not found!", "User cannot found with this id."),
    USERNAME_ERROR("Cannot be same username! ", "This username has been used. Write another username!"),
    PRICE_NOT_BE_NEGATIVE("Price cannot be negative!", "Price that was entered is little than zero."),
    TAX_RATE_NOT_BE_NEGATIVE("Tax rate cannot be negative!", "Tax rate that was entered is little than zero."),
    COMPARE_TWO_PRICES("First price error!", "First price must be little than second price."),
    ;

    private String errorMessage;
    private String messageDetails;

    GeneralErrorMessage(String errorMessage, String messageDetails) {
        this.errorMessage = errorMessage;
        this.messageDetails = messageDetails;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String getDetailMessage() {
        return this.messageDetails;
    }

}
