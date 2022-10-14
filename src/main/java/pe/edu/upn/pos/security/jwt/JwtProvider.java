package pe.edu.upn.pos.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pe.edu.upn.pos.security.UserDetailsImpl;
import pe.edu.upn.pos.util.RSAUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${jwt.expiration}")
    private long expiration;

    private static final String AUTHORITIES_KEY = "auth";

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

    public String generateToken(Authentication authentication) throws Exception {
        publicKey = RSAUtils.readPublicKey(new ClassPathResource("public-key.crt").getFile());
        privateKey = RSAUtils.readPrivateKey(new ClassPathResource("private-key.key").getFile());

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        //                                                          grantedAuthority -> {return grantedAuthority.getAuthority();}
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer("auth0")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + expiration))
                .withClaim(AUTHORITIES_KEY, authorities)
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token) throws Exception {
        publicKey = RSAUtils.readPublicKey(new ClassPathResource("public-key.crt").getFile());
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        return JWT.require(algorithm)
                .withIssuer("auth0")
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) throws Exception {
        publicKey = RSAUtils.readPublicKey(new ClassPathResource("public-key.crt").getFile());
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return false;
        }
        return true;
    }

}
