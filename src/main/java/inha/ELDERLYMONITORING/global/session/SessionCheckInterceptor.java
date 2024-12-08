package inha.ELDERLYMONITORING.global.session;

import inha.ELDERLYMONITORING.global.response.exception.GeneralException;
import inha.ELDERLYMONITORING.global.response.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class SessionCheckInterceptor implements HandlerInterceptor {

    private static final String[] whitelist = {"/api/members", "/api/members/login", "/error/**"};

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 인터셉터 체크 시작: {}", requestURI);

        if (isLoginCheckPath(requestURI)) {
            log.info("인증 체크 로직 실행: {}", requestURI);

            HttpSession session = request.getSession(false);
            if (session == null) {
                log.info("미인증 사용자 요청: {}", requestURI);
                throw new GeneralException(ErrorStatus.UNAUTHORIZED);
            }
        }

        return true;
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}


