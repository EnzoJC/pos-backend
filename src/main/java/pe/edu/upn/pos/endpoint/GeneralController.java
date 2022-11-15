package pe.edu.upn.pos.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upn.pos.dto.DocumentTypeDTO;
import pe.edu.upn.pos.dto.GenderDTO;
import pe.edu.upn.pos.dto.NationalityDTO;
import pe.edu.upn.pos.dto.RoleDTO;
import pe.edu.upn.pos.service.IDocumentTypeService;
import pe.edu.upn.pos.service.IGenderService;
import pe.edu.upn.pos.service.INationalityService;
import pe.edu.upn.pos.service.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/general")
public class GeneralController {

    private final IGenderService iGenderService;

    private final IDocumentTypeService iDocumentTypeService;

    private final INationalityService iNationalityService;

    private final IRoleService iRoleService;

    public GeneralController(IGenderService iGenderService, IDocumentTypeService iDocumentTypeService, INationalityService iNationalityService, IRoleService iRoleService) {
        this.iGenderService = iGenderService;
        this.iDocumentTypeService = iDocumentTypeService;
        this.iNationalityService = iNationalityService;
        this.iRoleService = iRoleService;
    }

    @GetMapping("/gender/all")
    public ResponseEntity<List<GenderDTO>> getAllGenders() {
        return ResponseEntity.ok(iGenderService.getAll());
    }

    @GetMapping("/document-type/all")
    public ResponseEntity<List<DocumentTypeDTO>> getAllDocumentTypes() {
        return ResponseEntity.ok(iDocumentTypeService.getAll());
    }

    @GetMapping("/nationality/all")
    public ResponseEntity<List<NationalityDTO>> getAllNationalities() {
        return ResponseEntity.ok(iNationalityService.getAll());
    }

    @GetMapping("/role/all")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(iRoleService.getAll());
    }

}
