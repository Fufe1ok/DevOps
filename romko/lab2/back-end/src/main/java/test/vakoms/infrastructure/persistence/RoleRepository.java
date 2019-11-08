package test.vakoms.infrastructure.persistence;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import test.vakoms.domain.Role;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Integer> {
}

