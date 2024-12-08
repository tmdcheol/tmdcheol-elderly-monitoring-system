package inha.ELDERLYMONITORING.global.argumentresolver;

import static inha.ELDERLYMONITORING.global.session.SessionConst.SESSION_KEY;

import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.global.response.exception.GeneralException;
import inha.ELDERLYMONITORING.global.response.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class) &&
                Member.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.error("session 에 회원 정보가 존재하지 않습니다.");
            throw new GeneralException(ErrorStatus.UNAUTHORIZED);
        }

        return session.getAttribute(SESSION_KEY);
    }
}

