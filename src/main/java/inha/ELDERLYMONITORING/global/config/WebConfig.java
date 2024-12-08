package inha.ELDERLYMONITORING.global.config;

import inha.ELDERLYMONITORING.global.argumentresolver.ClientIpArgumentResolver;
import inha.ELDERLYMONITORING.global.argumentresolver.LoginUserArgumentResolver;
import inha.ELDERLYMONITORING.global.session.SessionCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    /**
     * argumentResolver
     */
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    private final ClientIpArgumentResolver clientIpArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
        argumentResolvers.add(clientIpArgumentResolver);
    }

    /**
     * interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionCheckInterceptor())
                .order(1)
                .addPathPatterns("/**");
    }

    @Bean
    public SessionCheckInterceptor sessionCheckInterceptor() {
        return new SessionCheckInterceptor();
    }

}
