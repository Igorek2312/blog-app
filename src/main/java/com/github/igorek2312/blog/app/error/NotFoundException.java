package com.github.igorek2312.blog.app.error;

import org.springframework.http.HttpStatus;

/**
 * @author Igor Rybak
 */
public class NotFoundException extends CustomException {
    public NotFoundException() {
        messageCode="error.404";
    }

    public NotFoundException(String messageCode) {
        super(messageCode);
    }

    @Override
    public int getCode() {
        return 404;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
