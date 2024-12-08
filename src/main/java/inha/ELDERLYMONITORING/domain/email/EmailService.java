package inha.ELDERLYMONITORING.domain.email;

import inha.ELDERLYMONITORING.global.response.exception.GeneralException;
import inha.ELDERLYMONITORING.global.response.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void send(String email, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);

            log.info("메일전송 성공: {}", email);
        } catch (Exception e) {
            log.error("메일전송 실패 {}", email, e);
            throw new GeneralException(ErrorStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
