package inha.ELDERLYMONITORING.web;

import inha.ELDERLYMONITORING.domain.dangerevent.service.DangerEventService;
import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.global.argumentresolver.ClientIp;
import inha.ELDERLYMONITORING.global.argumentresolver.Login;
import inha.ELDERLYMONITORING.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/danger-event")
@Slf4j
public class DangerEventController {

    private final DangerEventService service;

    @PostMapping
    public ApiResponse<Void> occur(
            @Login Member member,
            @ClientIp String clientIp) {

        log.info("clientIp={}", clientIp);
        service.handle(member, clientIp);

        return ApiResponse.OK;
    }

}