package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Nationality;

public interface INationalityRepository extends JpaRepository<Nationality, Integer> {
    Boolean existsNationalityByName(String name);
}