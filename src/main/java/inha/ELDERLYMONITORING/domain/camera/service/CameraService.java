package inha.ELDERLYMONITORING.domain.camera.service;

import inha.ELDERLYMONITORING.domain.camera.Camera;
import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.domain.camera.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CameraService {

    private final CameraRepository cameraRepository;

    @Transactional
    public void addCamera(Member member, String clientIp) {
        cameraRepository
                .save(
                        Camera.builder()
                                .ip(clientIp)
                                .member(member)
                                .build()
                );
    }

    public List<Camera> lists(Member member) {
        return cameraRepository.findCamerasByMember(member);
    }

}
