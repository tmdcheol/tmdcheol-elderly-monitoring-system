package inha.ELDERLYMONITORING.domain.member.repository;

import inha.ELDERLYMONITORING.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
