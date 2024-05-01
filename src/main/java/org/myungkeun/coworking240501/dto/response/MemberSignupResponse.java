package org.myungkeun.coworking240501.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class MemberSignupResponse {
    private Long id;
    private String email;
    private String nickname;
}
