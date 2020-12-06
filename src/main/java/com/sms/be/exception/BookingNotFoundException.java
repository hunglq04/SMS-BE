package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class BookingNotFoundException extends RestException {

    public BookingNotFoundException() {
        super("Booking not found", ErrorMessage.BOOKING_NOT_FOUND);
    }

    public BookingNotFoundException(String message) {
        super(message, ErrorMessage.BOOKING_NOT_FOUND);
    }
}
