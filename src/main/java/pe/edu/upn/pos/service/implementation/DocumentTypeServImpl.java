package pe.edu.upn.pos.service.implementation;

import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.DocumentTypeDTO;
import pe.edu.upn.pos.entity.DocumentType;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.IDocumentTypeRepository;
import pe.edu.upn.pos.service.IDocumentTypeService;

import java.util.List;

@Service
public class DocumentTypeServImpl implements IDocumentTypeService {

    private final IDocumentTypeRepository iDocumentTypeRepository;

    private final GlobalMapper mapper;

    public DocumentTypeServImpl(IDocumentTypeRepository iDocumentTypeRepository, GlobalMapper mapper) {
        this.iDocumentTypeRepository = iDocumentTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DocumentTypeDTO> getAll() {
        List<DocumentType> documentTypeList = iDocumentTypeRepository.findAll();
        return mapper.mapListDocumentTypeEntityToListDocumentTypeDTO(documentTypeList);
    }
}
