package pe.edu.upn.pos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upn.pos.dto.*;
import pe.edu.upn.pos.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GlobalMapper {

    CompanyDTO mapCompanyEntityToCompanyDTO(Company company);

    @Mapping(target = "id", ignore = true)
    Company mapCompanyDTOToCompanyEntity(CompanyDTO companyDTO);

    GenderDTO mapGenderEntityToGenderDto(Gender gender);

    List<GenderDTO> mapListGenderEntityToListGenderDTO(List<Gender> genderList);

    DocumentTypeDTO mapDocumentTypeEntityToDocumentTypeDTO(DocumentType documentType);

    List<DocumentTypeDTO> mapListDocumentTypeEntityToListDocumentTypeDTO(List<DocumentType> documentTypeList);

    RoleDTO mapRoleEntityToRoleDTO(Role role);

    List<RoleDTO> mapListRoleEntityToListRoleDTO(List<Role> roleList);

    NationalityDTO mapNationalityEntityToNationalityDTO(Nationality nationality);

    List<NationalityDTO> mapListNationalityEntityToListNationalityDTO(List<Nationality> nationalityList);
}
