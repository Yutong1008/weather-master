package com.example.search.exception;

public class CityNotFound extends GlobalException{

    public CityNotFound(){
        super("Requested entity not present!", GloablErrorCode.ERROR_ENTITY_NOT_FOUND);
    }
    public CityNotFound(String msg) {
        super(msg, GloablErrorCode.ERROR_ENTITY_NOT_FOUND);
    }
}
