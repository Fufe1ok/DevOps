package test.vakoms.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.vakoms.domain.Car;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Integer> {
    Car findByRegistrationNumber(String registrationNumber);
    Car findByName(String name);
}
