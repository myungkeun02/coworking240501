package org.myungkeun.coworking240501.service.impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.coworking240501.domain.Member;
import org.myungkeun.coworking240501.domain.Platform;
import org.myungkeun.coworking240501.dto.request.MemberSignupRequest;
import org.myungkeun.coworking240501.dto.response.IsDuplicateEmailResponse;
import org.myungkeun.coworking240501.dto.response.IsDuplicateNicknameResponse;
import org.myungkeun.coworking240501.dto.response.MemberSignupResponse;
import org.myungkeun.coworking240501.exception.badRequest.DuplicateMemberException;
import org.myungkeun.coworking240501.exception.badRequest.InvalidEmailException;
import org.myungkeun.coworking240501.exception.badRequest.InvalidNicknameException;
import org.myungkeun.coworking240501.exception.badRequest.InvalidPasswordException;
import org.myungkeun.coworking240501.repository.MemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class MemberServiceImpl implements MemberService{
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,20}$");
    private static final int EMAIL_VERIFY_CODE_MAXIMUM_NUMBER = 9999;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public MemberSignupResponse signup(MemberSignupRequest request) {
        validatePassword(request.getPassword());
        validDuplicateMember(request);
        String encodePassword = passwordEncoder.encode(request.getPassword());

        try {
            Member member = new Member(request.getEmail(), request.getNickname(), encodePassword, Platform.EMAIL);
            Member save = memberRepository.save(member);
            MemberSignupResponse result = MemberSignupResponse.builder()
                    .id(save.getId())
                    .email(save.getEmail())
                    .nickname(save.getNickname())
                    .build();
            return result;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }
    }

    private void validDuplicateMember(MemberSignupRequest request) {
        if (memberRepository.existsByEmailAndPlatform(request.getEmail(), Platform.EMAIL)) {
            throw new DuplicateMemberException();
        }
    }

    private void validatePassword(String password) {
        if (!PASSWORD_REGEX.matcher(password).matches()) {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public IsDuplicateEmailResponse isDuplicateEmail(String email) {
        validEmail(email);
        Boolean isPresent = memberRepository.existsByEmailAndPlatform(email, Platform.EMAIL);
        return new IsDuplicateEmailResponse(isPresent);
    }

    private void validEmail(String email) {
        if (email.isBlank()) {
            throw new InvalidEmailException();
        }
    }

    @Override
    public IsDuplicateNicknameResponse isDuplicateNickname(String nickname) {
        validNickname(nickname);
        Boolean isPresent = memberRepository.existsByNickname(nickname);
        return new IsDuplicateNicknameResponse(isPresent);
    }

    private void validNickname(String nickname) {
        if (nickname.isBlank()) {
            throw new InvalidNicknameException();
        }
    }
}
