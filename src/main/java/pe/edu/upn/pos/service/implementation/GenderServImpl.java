package pe.edu.upn.pos.service.implementation;

import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.GenderDTO;
import pe.edu.upn.pos.entity.Gender;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.IGenderRepository;
import pe.edu.upn.pos.service.IGenderService;

import java.util.List;

@Service
public class GenderServImpl implements IGenderService {

    private final IGenderRepository genderRepository;

    private final GlobalMapper mapper;

    public GenderServImpl(IGenderRepository genderRepository, GlobalMapper mapper) {
        this.genderRepository = genderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GenderDTO> getAll() {
        List<Gender> genders = genderRepository.findAll();
        return mapper.mapListGenderEntityToListGenderDTO(genders);
    }
}
