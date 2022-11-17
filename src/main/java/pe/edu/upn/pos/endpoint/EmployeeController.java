package pe.edu.upn.pos.endpoint;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upn.pos.dto.response.EmployeeResponse;
import pe.edu.upn.pos.service.IEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "Employee", description = "Contains the Employee APIs")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageNo, pageSize, sortBy));
    }

    @GetMapping(params = "documentNumber")
    public ResponseEntity<EmployeeResponse> getEmployeeByDocumentNumber(@RequestParam("documentNumber") String documentNumber){
        return ResponseEntity.ok(employeeService.getEmployeeByDocumentNumber(documentNumber));
    }

    @GetMapping(params = "username")
    public ResponseEntity<EmployeeResponse> getEmployeeByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(employeeService.getEmployeeByUsername(username));
    }

    @GetMapping(params = "id")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

}
