package inha.ELDERLYMONITORING.domain.member;

import inha.ELDERLYMONITORING.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    @Builder
    private Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
