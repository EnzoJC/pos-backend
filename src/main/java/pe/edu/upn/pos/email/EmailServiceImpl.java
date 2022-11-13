package pe.edu.upn.pos.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.repository.ICompanyRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.lang.invoke.MethodHandles;

@Service
public class EmailServiceImpl implements IEmailSender {
    private static final Class<?> LOOKUP_CLASS = MethodHandles.lookup().lookupClass();
    private static final String CLASSNAME = LOOKUP_CLASS.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(CLASSNAME);

    @Value("${pe.edu.upn.pos.frontend.url}")
    private String frontendUrl;

    @Value("${pe.edu.upn.pos.frontend.path.email.confirmation}")
    private String frontendPathEmailConfirmation;

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final ICompanyRepository companyRepository;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, ICompanyRepository companyRepository) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.companyRepository = companyRepository;
    }

    @Override
    public void sendConfirmationToken(SignUpRequest request, String confirmationToken, String template) {
        String companyName = companyRepository.findFirstByOrderByIdAsc().isPresent() ? companyRepository.findFirstByOrderByIdAsc().get().getName() : "CompanyName";

        Context context = new Context();
        context.setVariable("givenNames", request.givenNames());
        context.setVariable("confirmationToken", confirmationToken);
        context.setVariable("confirmationTokenURL", frontendUrl + frontendPathEmailConfirmation);

        String process = this.templateEngine.process(template, context);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(process, true);
            helper.setTo(request.email());
            helper.setSubject("Confirmación de correo - " + companyName);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(String.format("Failed to send email to %s", request.email()), e);
            throw new IllegalArgumentException("Failed to send email for: " + request.email());
        }
    }
}
