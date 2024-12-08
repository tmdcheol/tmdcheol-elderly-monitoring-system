package inha.ELDERLYMONITORING.domain.camera;

import inha.ELDERLYMONITORING.domain.BaseEntity;
import inha.ELDERLYMONITORING.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Camera extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String ip;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Camera(String ip, Member member) {
        this.ip = ip;
        this.member = member;
    }

}