package pe.edu.upn.pos.service.implementation;

import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.RoleDTO;
import pe.edu.upn.pos.entity.Role;
import pe.edu.upn.pos.mapper.GlobalMapper;
import pe.edu.upn.pos.repository.IRoleRepository;
import pe.edu.upn.pos.service.IRoleService;

import java.util.List;

@Service
public class RoleServImpl implements IRoleService {

    private final IRoleRepository iRoleRepository;

    private final GlobalMapper mapper;

    public RoleServImpl(IRoleRepository iRoleRepository, GlobalMapper mapper) {
        this.iRoleRepository = iRoleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<Role> roleList = iRoleRepository.findAll();
        return mapper.mapListRoleEntityToListRoleDTO(iRoleRepository.findAll());
    }
}
