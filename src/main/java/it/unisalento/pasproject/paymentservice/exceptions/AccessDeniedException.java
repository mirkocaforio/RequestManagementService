package it.unisalento.pasproject.paymentservice.exceptions;

import it.unisalento.pasproject.paymentservice.exceptions.global.CustomErrorException;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends CustomErrorException {
    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
