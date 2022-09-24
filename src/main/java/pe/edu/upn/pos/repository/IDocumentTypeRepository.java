package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.DocumentType;

public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

    Boolean existsDocumentTypeByName(String name);
}