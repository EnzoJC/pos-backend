package pe.edu.upn.pos.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upn.pos.dto.CompanyDTO;
import pe.edu.upn.pos.service.ICompanyService;

@RestController
@RequestMapping("/api/company")
@Tag(name = "Company", description = "Contains the Company APIs")
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "Bearer"))
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.save(companyDTO);
        return ResponseEntity.ok("Created");
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'CASHIER', 'CASHIER_SUPERVISOR')")
//    @Operation(security = @SecurityRequirement(name = "Bearer"))
    @GetMapping
    public ResponseEntity<CompanyDTO> getCompany() {
        return ResponseEntity.ok(companyService.getCompany());
    }
}
