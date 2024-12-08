package inha.ELDERLYMONITORING.domain.member.service;

import inha.ELDERLYMONITORING.global.response.exception.GeneralException;
import inha.ELDERLYMONITORING.global.response.status.ErrorStatus;
import inha.ELDERLYMONITORING.global.security.SimplePasswordEncoder;
import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.domain.member.repository.MemberRepository;
import inha.ELDERLYMONITORING.web.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final SimplePasswordEncoder passwordEncoder;

    public void join(MemberRequest request) {
        checkDuplicate(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        memberRepository.save(member);
    }

    public Member login(MemberRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GeneralException(ErrorStatus.UNAUTHORIZED);
        }

        return member;
    }

    private void checkDuplicate(MemberRequest request) {
        String email = request.getEmail();
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new GeneralException(ErrorStatus.UNAUTHORIZED);
        }
    }

}
