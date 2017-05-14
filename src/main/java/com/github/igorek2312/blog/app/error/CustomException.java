package com.github.igorek2312.blog.app.error;

import org.springframework.http.HttpStatus;

/**
 * @author Igor Rybak
 */
public abstract class CustomException extends RuntimeException {
    protected Object[] messageArgs = null;
    protected String messageCode;

    public CustomException() {
    }

    public CustomException(String messageCode) {
        this.messageCode = messageCode;
    }

    public abstract int getCode();

    public abstract HttpStatus getHttpStatus();

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }
}
