package test.vakoms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.vakoms.domain.Car;
import test.vakoms.domain.Location;
import test.vakoms.domain.User;
import test.vakoms.exceptions.ResourceNotFoundException;
import test.vakoms.infrastructure.persistence.CarRepository;
import test.vakoms.infrastructure.persistence.LocationRepository;
import test.vakoms.infrastructure.persistence.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    public Set<Car> getCarsByUserId(Integer userId) {
        return userRepository.findById(userId).get().getCars();
    }

    public Set<Car> getCarsByLocationId(Integer roomId) {
        return locationRepository.findById(roomId).get().getCars();
    }

    public Car getCar(Integer id) throws ResourceNotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with this id is not found"));
    }

    @Transactional
    public Set<Car> findAllCarsByName(String name) {
        String regexp = "((\\S|^)*" + name.replaceAll("\\s+", "") + "(\\S|$)*|((\\S|^)*" + name.replaceAll("\\s+", "").toLowerCase(Locale.ENGLISH)
                + "(\\S|$)*)|((\\S|^)*" + name.replaceAll("\\s+", "").toUpperCase(Locale.ENGLISH) + "(\\S|$)*))";
        List<Car> cars = getAllCars();
        Set<Car> foundCars = new HashSet<>();
        for (Car car : cars) {
            if (car.getName().replaceAll("\\s+", "").matches(regexp)){
                foundCars.add(car);
            }
        }

        return foundCars;
    }

    @Transactional
    public void addCar(Car car) {
        String regexp = "\\s*|\\W*";
        if (!car.getName().matches(regexp) && !car.getType().matches(regexp) && !car.getRegistrationNumber().matches(regexp) && !car.getColour().matches(regexp)) {
            carRepository.save(car);
        }
    }

    @Transactional
    public void assignCarToUser(Integer deviceId, Integer userId) {
        Car car = carRepository.findById(deviceId).get();
        User user = userRepository.findById(userId).get();

        car.setUser(user);
        carRepository.save(car);
    }

    @Transactional
    public void assignCarToLocation(Integer deviceId, Integer roomId) {
        Car car = carRepository.findById(deviceId).get();
        Location location = locationRepository.findById(roomId).get();

        car.setLocation(location);
        carRepository.save(car);
    }

    @Transactional
    public void updateCar(Integer id, Car car) {
        Car carToUpdate = carRepository.findById(id).get();

        if (!car.getName().isEmpty()) {
            carToUpdate.setName(car.getName());
        }

        if (!car.getType().isEmpty()) {
            carToUpdate.setType(car.getType());
        }

        if (!car.getRegistrationNumber().isEmpty()) {
            carToUpdate.setRegistrationNumber(car.getRegistrationNumber());
        }

        if (!car.getColour().isEmpty()) {
            carToUpdate.setColour(car.getColour());
        }

        if (car.getUser() != null) {
            carToUpdate.setUser(car.getUser());
        }

        if (car.getLocation() != null) {
            carToUpdate.setLocation(car.getLocation());
        }

        carRepository.save(carToUpdate);
    }

    @Transactional
    public void updateCarUser(Integer id, Integer userId) {
        User user = userRepository.findById(userId).get();
        Car carToUpdate = carRepository.findById(id).get();

        carToUpdate.setUser(user);

        carRepository.save(carToUpdate);
    }

    @Transactional
    public void updateCarLocation(Integer id, Integer roomId) {
        Location location = locationRepository.findById(roomId).get();
        Car carToUpdate = carRepository.findById(id).get();

        carToUpdate.setLocation(location);

        carRepository.save(carToUpdate);
    }

    @Transactional
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void deleteCarUser(Integer id) {
        Car carToUpdate = carRepository.findById(id).get();

        carToUpdate.setUser(null);

        carRepository.save(carToUpdate);
    }

    @Transactional
    public Car findCarByRegistrationNumber(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber);
    }
}
