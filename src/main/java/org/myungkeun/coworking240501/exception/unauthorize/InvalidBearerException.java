package org.myungkeun.coworking240501.exception.unauthorize;


public class InvalidBearerException extends UnauthorizedException{
    public InvalidBearerException() {
        super("로그인이 필요한 서비스입니다.", 1013);
    }
}
