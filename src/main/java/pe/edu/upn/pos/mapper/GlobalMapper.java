package pe.edu.upn.pos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upn.pos.dto.CompanyDTO;
import pe.edu.upn.pos.entity.Company;

@Mapper(componentModel = "spring")
public interface GlobalMapper {

    CompanyDTO mapCompanyEntityToCompanyDTO(Company company);

    @Mapping(target = "id", ignore = true)
    Company mapCompanyDTOToCompanyEntity(CompanyDTO companyDTO);
}
