package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.DataSheet;
import pe.edu.upn.pos.entity.DataSheetId;

public interface IDataSheetRepository extends JpaRepository<DataSheet, DataSheetId> {
}