package org.myungkeun.coworking240501.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class BaseResponse<T> {
    private Long statusCode;
    private T data;
}
