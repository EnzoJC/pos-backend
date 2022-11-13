package pe.edu.upn.pos.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upn.pos.dto.request.LoginRequest;
import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.dto.response.LoginResponse;
import pe.edu.upn.pos.dto.response.VerificationEmailResponse;
import pe.edu.upn.pos.security.UserDetailsImpl;
import pe.edu.upn.pos.security.jwt.JwtProvider;
import pe.edu.upn.pos.service.IUserAccountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization", description = "Contains the Login and Sign Up APIs")
public class AuthController {
    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final IUserAccountService userAccountService;

    public AuthController(JwtProvider jwtProvider, AuthenticationManager authenticationManager, IUserAccountService userAccountService) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.userAccountService = userAccountService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "Bearer"))
    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello");
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = LoginRequest.class))
                                    )
                            }
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new LoginResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getAuthorities().stream().findFirst().orElseThrow(() -> new Exception("No role found")).getAuthority()
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "Bearer"))
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userAccountService.save(signUpRequest);
        return ResponseEntity.ok("Signup");
    }

    @GetMapping("/email-verification")
    public ResponseEntity<VerificationEmailResponse> verifyEmail(@RequestParam(name = "token") String token) {
        VerificationEmailResponse verificationEmailResponse = userAccountService.verifyEmail(token);
        return ResponseEntity.ok(verificationEmailResponse);
    }

}
