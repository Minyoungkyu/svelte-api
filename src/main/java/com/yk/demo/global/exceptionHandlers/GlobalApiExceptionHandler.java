package com.yk.demo.global.exceptionHandlers;

import com.yk.demo.global.excptions.GlobalException;
import com.yk.demo.global.rq.Rq.Rq;
import com.yk.demo.global.rsData.RsData;
import com.yk.demo.standard.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class GlobalApiExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getRsData().getStatusCode());
        rq.setStatusCode(ex.getRsData().getStatusCode());

        return new ResponseEntity<>(ex.getRsData(), status);
    }
}
