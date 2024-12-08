package inha.ELDERLYMONITORING.global.argumentresolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class ClientIpArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String IP_HEADER = "X-Forwarded-For";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ClientIp.class) &&
                String.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        String clientIp = request.getHeader(IP_HEADER);
        clientIp = (clientIp == null || clientIp.isEmpty()) ?
                request.getRemoteAddr() : clientIp.split(",")[0];
        return clientIp;
    }
}
