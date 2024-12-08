package inha.ELDERLYMONITORING.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberRequest {

    @NotEmpty(message = "빈 값입니다.")
    @Email
    String email;

    @NotEmpty(message = "빈 값입니다.")
    String password;
}
