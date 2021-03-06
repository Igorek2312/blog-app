package com.github.igorek2312.blog.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Igor Rybak
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends CustomException {

    public NotFoundException(String messageCode, Object... messageArgs) {
        super(messageCode, messageArgs);
    }

    @Override
    public int getCode() {
        return 404;
    }
}
