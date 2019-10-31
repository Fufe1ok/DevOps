package test.vakoms.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.vakoms.domain.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Integer> {
}

