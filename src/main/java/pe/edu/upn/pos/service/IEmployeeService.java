package pe.edu.upn.pos.service;

import pe.edu.upn.pos.dto.response.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

    EmployeeResponse getEmployeeByDocumentNumber(String documentNumber);

    EmployeeResponse getEmployeeByUsername(String username);

    EmployeeResponse getEmployeeById(int id);
}
