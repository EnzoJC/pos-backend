package pe.edu.upn.pos.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
public class ApiErrorResponse<T> {
    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.ofHours(-5));
    private List<T> errors;

    public ApiErrorResponse() { }

    public ApiErrorResponse(HttpStatus status, List<T> errors) {
        this.status = status;
        this.errors = errors;
    }
}
