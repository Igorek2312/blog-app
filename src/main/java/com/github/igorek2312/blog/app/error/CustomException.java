package com.github.igorek2312.blog.app.error;

import java.text.MessageFormat;

/**
 * @author Igor Rybak
 */
public abstract class CustomException extends RuntimeException {
    protected Object[] messageArgs = null;
    protected String detailsMessage;

    public CustomException() {
    }

    public CustomException(String detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    public CustomException(String detailsMessage, Object[] messageArgs) {
        this.detailsMessage = detailsMessage;
        this.messageArgs = messageArgs;
    }

    public abstract int getCode();

    public String getDetailsMessage() {
        return detailsMessage;
    }

    public void setDetailsMessage(String detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object... messageArgs) {
        this.messageArgs = messageArgs;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format(detailsMessage, messageArgs);
    }
}
