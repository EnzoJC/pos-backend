package pe.edu.upn.pos.service;

import pe.edu.upn.pos.dto.DocumentTypeDTO;

import java.util.List;

public interface IDocumentTypeService {

    List<DocumentTypeDTO> getAll();
}
