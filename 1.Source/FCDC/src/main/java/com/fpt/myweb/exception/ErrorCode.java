package com.fpt.myweb.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    PROCESS_SUCCESS("00000", "All thing done"),
    AUTHENTICATION_FAILED("00002", "Authentication failed"),
    INTERNAL_SERVER_ERROR("00001", "Internal server error");

    private String key;
    private String value;

    private ErrorCode(String key, String value){
        this.key = key;
        this.value = value;
    }
}
