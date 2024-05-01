package org.myungkeun.coworking240501.exception.unauthorize;

import org.myungkeun.coworking240501.exception.CoworkingException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CoworkingException {
    public UnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
}
