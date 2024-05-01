package org.myungkeun.coworking240501.repository;

import org.myungkeun.coworking240501.domain.Member;
import org.myungkeun.coworking240501.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPlatform(String email, Platform platform);

    Boolean existsByEmailAndPlatform(String email, Platform platform);

    Boolean existsByNickname(String nickname);
}
