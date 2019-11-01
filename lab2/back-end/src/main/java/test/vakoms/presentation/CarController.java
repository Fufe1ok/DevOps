package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.vakoms.application.FareService;
import test.vakoms.application.CarService;
import test.vakoms.domain.Car;
import test.vakoms.dto.CarDTO;
import test.vakoms.exceptions.ResourceNotFoundException;

import java.util.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "https://lab2-frontend.appspot.com")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private FareService fareService;

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        List<Car> cars = carService.getAllCars();
        Link link = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();

        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car entity : cars) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CarDTO dto = new CarDTO(entity, selfLink);
            carDTOList.add(dto);
        }

        return carDTOList;
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer carId) throws ResourceNotFoundException {
        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();
        return ResponseEntity.ok().body(new CarDTO(carService.getCar(carId), link));
    }

    @GetMapping(value = "/users/{userId}/cars")
    public ResponseEntity<Set<CarDTO>> getCarsByUserId(@PathVariable Integer userId) {
        Set<Car> cars = carService.getCarsByUserId(userId);

        Link link = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();

        Set<CarDTO> carsSet = new HashSet<>();
        for (Car entity : cars) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CarDTO dto = new CarDTO(entity, selfLink);
            carsSet.add(dto);
        }

        return new ResponseEntity<>(carsSet, HttpStatus.OK);
    }

    @GetMapping(value = "/locations/{locationId}/cars")
    public ResponseEntity<Set<CarDTO>> getCarsByLocationId(@PathVariable Integer locationId) {
        Set<Car> cars = carService.getCarsByLocationId(locationId);

        Link link = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();

        Set<CarDTO> carsSet = new HashSet<>();
        for (Car entity : cars) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CarDTO dto = new CarDTO(entity, selfLink);
            carsSet.add(dto);
        }

        return new ResponseEntity<>(carsSet, HttpStatus.OK);
    }

    @GetMapping("/search/cars/{name}")
    public Set<CarDTO> findAllCarsByName(@PathVariable String name) {
        Set<Car> cars = carService.findAllCarsByName(name);
        Link link = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();

        Set<CarDTO> carDTOSet = new HashSet<>();
        for (Car entity : cars) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CarDTO dto = new CarDTO(entity, selfLink);
            carDTOSet.add(dto);
        }

        return carDTOSet;
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDTO> addCar(@RequestBody Car car) throws ResourceNotFoundException {
        carService.addCar(car);

        Link link = linkTo(methodOn(CarController.class).getCar(car.getId())).withSelfRel();

        return new ResponseEntity<>(new CarDTO(car, link), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/cars/{carId}")
    public ResponseEntity<CarDTO> assignCarToUser(@PathVariable Integer carId, @PathVariable Integer userId) throws ResourceNotFoundException {
        carService.assignCarToUser(carId, userId);

        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();

        return new ResponseEntity<>(new CarDTO(carService.getCar(carId), link), HttpStatus.OK);
    }

    @PostMapping("/locations/{locationId}/cars/{carId}")
    public ResponseEntity<CarDTO> assignCarToLocation(@PathVariable Integer carId, @PathVariable Integer locationId) throws ResourceNotFoundException {
        carService.assignCarToLocation(carId, locationId);

        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();

        return new ResponseEntity<>(new CarDTO(carService.getCar(carId), link), HttpStatus.OK);
    }

    @PutMapping("/cars/{carId}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Integer carId, @RequestBody Car car) throws ResourceNotFoundException {
        carService.updateCar(carId, car);

        Car updatedCar = carService.getCar(carId);
        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();

        return new ResponseEntity<>(new CarDTO(updatedCar, link), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/cars/{carId}")
    public ResponseEntity<CarDTO> updateCarUser(@PathVariable Integer carId, @PathVariable Integer userId) throws ResourceNotFoundException {
        carService.updateCarUser(carId, userId);

        Car updatedCar = carService.getCar(carId);
        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();

        return new ResponseEntity<>(new CarDTO(updatedCar, link), HttpStatus.OK);
    }

    @PutMapping("/locations/{locationId}/cars/{carId}")
    public ResponseEntity<CarDTO> updateCarLocation(@PathVariable Integer carId, @PathVariable Integer locationId) throws ResourceNotFoundException {
        carService.updateCarLocation(carId, locationId);

        Car updatedCar = carService.getCar(carId);
        Link link = linkTo(methodOn(CarController.class).getCar(carId)).withSelfRel();

        return new ResponseEntity<>(new CarDTO(updatedCar, link), HttpStatus.OK);
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity deleteCar(@PathVariable Integer carId) {
        carService.deleteCar(carId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/cars/{carId}")
    public ResponseEntity deleteCarUser(@PathVariable Integer carId) {
        carService.deleteCarUser(carId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
