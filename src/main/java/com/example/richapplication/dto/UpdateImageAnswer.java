package com.example.richapplication.dto;

public class UpdateImageAnswer {
    private String newUrl;

    public UpdateImageAnswer(String newUrl) {
        this.newUrl = newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }
}
