package test.vakoms.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.vakoms.domain.Fare;

@RepositoryRestResource
public interface FareRepository extends CrudRepository<Fare, Integer> {
    Fare findByUuid(String uuid);
}
