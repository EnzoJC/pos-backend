package pe.edu.upn.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse<T> {
    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<T> errors;
}
