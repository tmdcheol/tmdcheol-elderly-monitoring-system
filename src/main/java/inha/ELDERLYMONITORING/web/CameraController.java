package inha.ELDERLYMONITORING.web;

import inha.ELDERLYMONITORING.global.argumentresolver.ClientIp;
import inha.ELDERLYMONITORING.global.argumentresolver.Login;
import inha.ELDERLYMONITORING.domain.member.Member;
import inha.ELDERLYMONITORING.domain.camera.service.CameraService;
import inha.ELDERLYMONITORING.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cameras")
@Slf4j
public class CameraController {

    private final CameraService cameraService;

    @PostMapping
    public ApiResponse<Void> addCamera(@Login Member member, @ClientIp String clientIp) {
        log.info("clientIp={}", clientIp);
        cameraService.addCamera(member, clientIp);

        return ApiResponse.OK;
    }

}
