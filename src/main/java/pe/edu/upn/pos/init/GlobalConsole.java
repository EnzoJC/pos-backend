package pe.edu.upn.pos.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.upn.pos.entity.*;
import pe.edu.upn.pos.repository.*;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GlobalConsole implements CommandLineRunner {
    private static final Class<?> LOOKUP_CLASS = MethodHandles.lookup().lookupClass();
    private static final String CLASSNAME = LOOKUP_CLASS.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(CLASSNAME);

    /*
    TODO: Estos permisos deben ser especificos para cada rol teniendo en cuenta a los modulos a los que tiene acceso.
        Por ejemplo, el rol de administrador tendrá acceso a todos los modulos, pero el rol de cajero solo tendrá acceso
        a los modulos de ventas y caja.
        Por lo tanto, los permisos de administrador serán más amplios que los de cajero.
        Ejemplo de permisos de administrador: "OP_ADMINISTRADOR", "OP_CAJERO", "OP_VENTAS", "OP_COMPRAS", "OP_INVENTARIO", "OP_CLIENTES", "OP_PROVEEDORES", "OP_PRODUCTOS", "OP_USUARIOS"
        Ejemplo de permisos de cajero: "OP_CAJERO", "OP_VENTAS", "OP_CLIENTES"

        TODO: Por el momento, solo se creará permisos básicos.
    */
    private final List<Permission> permissions = List.of(
            new Permission(null, "OP_READ"),
            new Permission(null, "OP_WRITE"),
            new Permission(null, "OP_UPDATE"),
            new Permission(null, "OP_DELETE")
    );

    private final Set<Permission> grantedPermissions = new HashSet<>(permissions);
    private final List<Role> roles = List.of(
            new Role(null, "ROLE_ADMIN", grantedPermissions),
            new Role(null, "ROLE_CASHIER", grantedPermissions),
            new Role(null, "ROLE_CASHIER_SUPERVISOR", grantedPermissions)
    );
    private final List<Nationality> nationalities = List.of(
            new Nationality(null, "Peruana"),
            new Nationality(null, "Colombiana"),
            new Nationality(null, "Chilena"),
            new Nationality(null, "Argentina"),
            new Nationality(null, "Boliviana"),
            new Nationality(null, "Brasileña"),
            new Nationality(null, "Venezolana"),
            new Nationality(null, "Paraguaya"),
            new Nationality(null, "Uruguaya"),
            new Nationality(null, "Ecuatoriana"),
            new Nationality(null, "Otra")
    );

    private final List<Gender> genders = List.of(
            new Gender(null, "Masculino"),
            new Gender(null, "Femenino"),
            new Gender(null, "Otro")
    );

    private final List<DocumentType> documentTypes = List.of(
            new DocumentType(null, "DNI", 8),
            new DocumentType(null, "Carnet de Extranjería", 12),
            new DocumentType(null, "Pasaporte", 9)
    );

    private final List<CurrencyType> currencyTypes = List.of(
            new CurrencyType(null, "Soles", "S/"),
            new CurrencyType(null, "Dólares", "US$"),
            new CurrencyType(null, "Euros", "€")
    );

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;
    private final INationalityRepository nationalityRepository;
    private final IGenderRepository genderRepository;
    private final IDocumentTypeRepository documentTypeRepository;

    private final ICurrencyTypeRepository currencyTypeRepository;
    private final IUserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${pe.edu.upn.pos.admin.user}")
    private String adminUser;

    @Value("${pe.edu.upn.pos.admin.password}")
    private String adminPassword;

    public GlobalConsole(IRoleRepository roleRepository, IPermissionRepository permissionRepository,
                         INationalityRepository nationalityRepository, IGenderRepository genderRepository,
                         IDocumentTypeRepository documentTypeRepository, ICurrencyTypeRepository currencyTypeRepository,
                         IUserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.nationalityRepository = nationalityRepository;
        this.genderRepository = genderRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.currencyTypeRepository = currencyTypeRepository;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>> Starting insertions in database...");

        permissions.stream()
                .filter(permission -> !permissionRepository.existsPermissionByName(permission.getName()))
                .forEach(permissionRepository::save); // equivalent to: permission -> permissionRepository.save(permission)

        var permissionsInDb = new HashSet<>(permissionRepository.findAll());

        roles.stream()
                .filter(role -> !roleRepository.existsRoleByName(role.getName()))
                .forEach(role -> {
                    role.setPermissions(permissionsInDb);
                    roleRepository.save(role);
                });

        nationalities.stream()
                .filter(nationality -> !nationalityRepository.existsNationalityByName(nationality.getName()))
                .forEach(nationalityRepository::save);

        genders.stream()
                .filter(gender -> !genderRepository.existsGenderByName(gender.getName()))
                .forEach(genderRepository::save);

        documentTypes.stream()
                .filter(documentType -> !documentTypeRepository.existsDocumentTypeByType(documentType.getType()))
                .forEach(documentTypeRepository::save);

        currencyTypes.stream()
                .filter(currencyType -> !currencyTypeRepository.existsCurrencyTypeByCurrency(currencyType.getCurrency()))
                .forEach(currencyTypeRepository::save);


        if (!userAccountRepository.existsByUsername(adminUser)) {
            logger.info(">>>> Creating the admin user...");

            var adminRole = roleRepository.findByName("ROLE_ADMIN");
            UserAccount adminUserAccount = UserAccount.builder()
                    .username(adminUser)
                    .passwordHash(passwordEncoder.encode(adminPassword))
                    .email("admin@mail.com")
                    .role(adminRole.orElseThrow(() -> new RuntimeException("Role for admin user not found")))
                    .isEmailVerified(true)
                    .build();

            userAccountRepository.save(adminUserAccount);

            logger.info(">>>> Admin user created successfully!");
        }

        logger.info(">>>> Finishing insertions in database...");
    }
}
