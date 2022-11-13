package pe.edu.upn.pos.service;

import pe.edu.upn.pos.dto.CompanyDTO;

public interface ICompanyService {

    void save(CompanyDTO companyDTO);

    CompanyDTO getCompany();
}
