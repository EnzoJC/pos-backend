package pe.edu.upn.pos.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.response.EmployeeResponse;
import pe.edu.upn.pos.entity.Employee;
import pe.edu.upn.pos.entity.UserAccount;
import pe.edu.upn.pos.exception.NoDataFoundException;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.IEmployeeRepository;
import pe.edu.upn.pos.repository.IUserAccountRepository;
import pe.edu.upn.pos.service.IEmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    private final IUserAccountRepository userAccountRepository;

    private final GlobalMapper globalMapper;

    public EmployeeServImpl(IEmployeeRepository employeeRepository, IUserAccountRepository userAccountRepository, GlobalMapper globalMapper) {
        this.employeeRepository = employeeRepository;
        this.userAccountRepository = userAccountRepository;
        this.globalMapper = globalMapper;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        return pagedResult.hasContent() ? globalMapper.mapListEmployeeEntityToListEmployeeResponse(pagedResult.getContent()) : new ArrayList<>();
    }

    @Override
    public EmployeeResponse getEmployeeByDocumentNumber(String documentNumber) {
        Employee employee = employeeRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> new NoDataFoundException("No se encontró el empleado con el id: " + documentNumber));
        return globalMapper.mapEmployeeEntityToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse getEmployeeByUsername(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(() -> new NoDataFoundException("No se encontró el usuario con el nombre de usuario: " + username));
        Employee employee = employeeRepository.findByUserAccount(userAccount).orElseThrow(() -> new NoDataFoundException("No se encontró el empleado con el id: " + username));
        return globalMapper.mapEmployeeEntityToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No se encontró el empleado con el id: " + id));
        return globalMapper.mapEmployeeEntityToEmployeeResponse(employee);
    }
}
