package pe.edu.upn.pos.service.implementation;

import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.NationalityDTO;
import pe.edu.upn.pos.entity.Nationality;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.INationalityRepository;
import pe.edu.upn.pos.service.INationalityService;

import java.util.List;

@Service
public class NationalityServImpl implements INationalityService {

    private final INationalityRepository iNationalityRepository;

    private final GlobalMapper mapper;

    public NationalityServImpl(INationalityRepository iNationalityRepository, GlobalMapper mapper) {
        this.iNationalityRepository = iNationalityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<NationalityDTO> getAll() {
        List<Nationality> nationalityList = iNationalityRepository.findAll();
        return mapper.mapListNationalityEntityToListNationalityDTO(nationalityList);
    }
}
