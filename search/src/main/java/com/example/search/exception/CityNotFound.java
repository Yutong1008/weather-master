package com.example.search.exception;

public class CityNotFound extends RuntimeException{
    public CityNotFound(String msg){
        super(msg);
    }
}
