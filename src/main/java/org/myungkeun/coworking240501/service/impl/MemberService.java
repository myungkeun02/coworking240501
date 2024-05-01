package org.myungkeun.coworking240501.service.impl;

import org.myungkeun.coworking240501.dto.request.MemberSignupRequest;
import org.myungkeun.coworking240501.dto.response.IsDuplicateEmailResponse;
import org.myungkeun.coworking240501.dto.response.IsDuplicateNicknameResponse;
import org.myungkeun.coworking240501.dto.response.MemberSignupResponse;

public interface MemberService {
    MemberSignupResponse signup(MemberSignupRequest request);

    IsDuplicateEmailResponse isDuplicateEmail(String email);

    IsDuplicateNicknameResponse isDuplicateNickname(String nickname);
}
