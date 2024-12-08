package inha.ELDERLYMONITORING.domain.camera.repository;

import inha.ELDERLYMONITORING.domain.camera.Camera;
import inha.ELDERLYMONITORING.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {

    List<Camera> findCamerasByMember(Member member);

}
