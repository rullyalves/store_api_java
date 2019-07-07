package com.store.exceptions;

public class ErrorDetails {

    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String developerMessage;

    public ErrorDetails title(String title) {
        this.title = title;
        return this;
    }

    public ErrorDetails status(int status) {
        this.status = status;
        return this;
    }

    public ErrorDetails details(String details) {
        this.details = details;
        return this;
    }

    public ErrorDetails timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ErrorDetails developerMessage(String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

}