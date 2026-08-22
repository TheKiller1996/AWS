package com.infor.AWS.exception;

public class ResourceNotFoundException extends AwsApiException{

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
