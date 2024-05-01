package org.myungkeun.coworking240501.exception.badRequest;

public class BlankTokenException extends BadRequestException{
    public BlankTokenException() {
        super("토큰은 공백일 수 없습니다.", 1018);
    }
}
