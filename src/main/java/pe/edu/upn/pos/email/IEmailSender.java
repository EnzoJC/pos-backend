package pe.edu.upn.pos.email;

import pe.edu.upn.pos.dto.request.SignUpRequest;

public interface IEmailSender {
    void sendConfirmationToken(SignUpRequest request, String confirmationToken, String template);
}