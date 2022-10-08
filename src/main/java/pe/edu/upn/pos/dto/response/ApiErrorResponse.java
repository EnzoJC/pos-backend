package pe.edu.upn.pos.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public record ApiErrorResponse<T>(HttpStatus status, LocalDateTime timestamp, List<T> errors) {
    public ApiErrorResponse(HttpStatus status, List<T> errors) {
        this(status, LocalDateTime.now(ZoneOffset.ofHours(-5)), errors);
    }
}