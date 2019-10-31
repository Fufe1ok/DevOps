package test.vakoms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.vakoms.domain.Car;
import test.vakoms.domain.Location;
import test.vakoms.exceptions.ResourceNotFoundException;
import test.vakoms.infrastructure.persistence.CarRepository;
import test.vakoms.infrastructure.persistence.LocationRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }

    public Location getLocation(Integer id) throws ResourceNotFoundException {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with this id is not found"));
    }

    @Transactional
    public Set<Location> findAllLocationsByName(String name) {
        String regexp = "((\\S|^)*" + name.replaceAll("\\s+", "") + "(\\S|$)*|((\\S|^)*" + name.replaceAll("\\s+", "").toLowerCase(Locale.ENGLISH)
                + "(\\S|$)*)|((\\S|^)*" + name.replaceAll("\\s+", "").toUpperCase(Locale.ENGLISH) + "(\\S|$)*))";
        List<Location> locations = getAllLocations();
        Set<Location> foundLocations = new HashSet<>();
        for (Location location : locations) {
            if (location.getName().matches(regexp)) {
                foundLocations.add(location);
            }
        }

        return foundLocations;
    }

    @Transactional
    public void addLocation(Location location) {
        String regexp = "\\s*|\\W*";
        if (!location.getName().matches(regexp) && !location.getDescription().matches(regexp)) {
            locationRepository.save(location);
        }
    }

    @Transactional
    public void updateLocation(Integer id, Location location) {
        Location locationToUpdate = locationRepository.findById(id).get();

        if (!location.getName().isEmpty()) {
            locationToUpdate.setName(location.getName());
        }

        if (!location.getDescription().isEmpty()) {
            locationToUpdate.setDescription(location.getDescription());
        }

        if (!location.getCars().isEmpty()) {
            locationToUpdate.setCars(location.getCars());
        }

        locationRepository.save(locationToUpdate);
    }

    @Transactional
    public void deleteLocation(Integer id) {
        Location location = locationRepository.findById(id).get();

        Set<Car> roomCars = location.getCars();

        if (!roomCars.isEmpty()) {
            roomCars.forEach(x -> {
                Car car = carRepository.findById(x.getId()).get();
                car.setLocation(null);
                carRepository.save(car);
            });
            location.setCars(new HashSet<>());
        }

        locationRepository.deleteById(id);
    }

}
