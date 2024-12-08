package inha.ELDERLYMONITORING.web;

import static inha.ELDERLYMONITORING.global.session.SessionConst.SESSION_KEY;

import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.domain.member.service.MemberService;
import inha.ELDERLYMONITORING.global.response.ApiResponse;
import inha.ELDERLYMONITORING.web.dto.MemberRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<Void> join(
            @Valid @RequestBody MemberRequest memberRequest
    ) {
        memberService.join(memberRequest);
        return ApiResponse.OK;
    }

    @PostMapping("/login")
    public ApiResponse<Void> login(
            @Valid @RequestBody MemberRequest memberRequest,
            HttpServletRequest request
    ) {
        Member loginMember = memberService.login(memberRequest);

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_KEY, loginMember);

        return ApiResponse.OK;
    }

}
