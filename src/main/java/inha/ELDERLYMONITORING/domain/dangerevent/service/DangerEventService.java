package inha.ELDERLYMONITORING.domain.dangerevent.service;

import inha.ELDERLYMONITORING.domain.dangerevent.DangerEvent;
import inha.ELDERLYMONITORING.domain.dangerevent.repository.DangerEventRepository;
import inha.ELDERLYMONITORING.domain.email.EmailService;
import inha.ELDERLYMONITORING.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DangerEventService {

    private static final String SUBJECT = "[TEST]: 이상상태가 감지되었습니다.";
    private static final String CONTENT = "이상상태 감지 IP: %s";

    private final EmailService emailService;
    private final DangerEventRepository repository;

    public void handle(Member member, String clientIp) {
        repository.save(DangerEvent.builder()
                .member(member)
                .build());

        /**
         * 비동기로 보내면 더 좋을 것.
         */
        emailService.send(member.getEmail(), SUBJECT, String.format(CONTENT, clientIp));
    }

}
