package com.kinobooking.secure.exceptions;

/**
 * Created by Екатерина on 16.08.2017.
 */
@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
