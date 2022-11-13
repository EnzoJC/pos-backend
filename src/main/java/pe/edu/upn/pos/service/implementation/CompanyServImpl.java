package pe.edu.upn.pos.service.implementation;

import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.CompanyDTO;
import pe.edu.upn.pos.entity.Company;
import pe.edu.upn.pos.exception.InsertionLockedException;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.ICompanyRepository;
import pe.edu.upn.pos.service.ICompanyService;

@Service
public class CompanyServImpl implements ICompanyService {

    private final GlobalMapper mapper;

    private final ICompanyRepository companyRepository;

    public CompanyServImpl(ICompanyRepository companyRepository, GlobalMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(CompanyDTO companyDTO) {
        boolean isCompanyRegistered = companyRepository.findFirstByOrderByIdAsc().isPresent();
        if (isCompanyRegistered) {
            throw new InsertionLockedException();
        } else {
            companyRepository.save(mapper.mapCompanyDTOToCompanyEntity(companyDTO));
        }
    }

    @Override
    public CompanyDTO getCompany() {
        Company company = companyRepository.findFirstByOrderByIdAsc().orElse(new Company());
        return mapper.mapCompanyEntityToCompanyDTO(company);
    }

}
