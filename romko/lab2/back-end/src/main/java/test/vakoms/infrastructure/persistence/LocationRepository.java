package test.vakoms.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.vakoms.domain.Location;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findAllByName(String name);
}
