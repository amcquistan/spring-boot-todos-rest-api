package com.thecodinginterface.todobackend.models;

public enum TodoStatus {
    Incomplete("I"),
    Completed("C"),
    Archived("A");

    private String code;
    private TodoStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
