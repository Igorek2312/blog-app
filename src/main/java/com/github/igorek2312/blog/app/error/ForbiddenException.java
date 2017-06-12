package com.github.igorek2312.blog.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Igor Rybak
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends CustomException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    @Override
    public int getCode() {
        return 403;
    }
}
