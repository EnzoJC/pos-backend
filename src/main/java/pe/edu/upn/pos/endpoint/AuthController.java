package pe.edu.upn.pos.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class Auth {
    String s;

    @GetMapping("/test")
    public String test() throws IOException {
        s = new ClassPathResource("RS256.key.pub").getFile().getPath();
        return s;
    }
}
