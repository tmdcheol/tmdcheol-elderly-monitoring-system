package inha.ELDERLYMONITORING.global.response.exception.handler;

import inha.ELDERLYMONITORING.global.response.ApiResponse;
import inha.ELDERLYMONITORING.global.response.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException ex) {
        log.error("Error Code: {}, Message: {}, Data: {}",
                ex.getErrorStatus().getCode(),
                ex.getErrorStatus().getMessage(),
                ex.getData() != null ? ex.getData() : "No additional data",
                ex
        );

        return ResponseEntity
                .status(ex.getErrorStatus().getHttpStatus())
                .body(ApiResponse.onFailure(
                        ex.getErrorStatus(),
                        ex.getData()
                ));
    }
}