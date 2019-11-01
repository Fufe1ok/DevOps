package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.vakoms.application.LocationService;
import test.vakoms.domain.Location;
import test.vakoms.dto.LocationDTO;
import test.vakoms.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "https://lab2-frontend.appspot.com")
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        Link link = linkTo(methodOn(LocationController.class).getAllLocations()).withSelfRel();

        List<LocationDTO> locationDTOList = new ArrayList<>();
        for (Location entity : locations) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            LocationDTO dto = new LocationDTO(entity, selfLink);
            locationDTOList.add(dto);
        }

        return locationDTOList;
    }

    @GetMapping("/locations/{locationId}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Integer locationId) throws ResourceNotFoundException {
        Link link = linkTo(methodOn(LocationController.class).getLocation(locationId)).withSelfRel();

        return ResponseEntity.ok().body(new LocationDTO(locationService.getLocation(locationId), link));
    }

    @GetMapping("/search/locations/{name}")
    public Set<LocationDTO> findAllLocationsByName(@PathVariable String name) {
        Set<Location> locations = locationService.findAllLocationsByName(name);
        Link link = linkTo(methodOn(LocationController.class).getAllLocations()).withSelfRel();

        Set<LocationDTO> locationDTOSet = new HashSet<>();
        for (Location entity : locations) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            LocationDTO dto = new LocationDTO(entity, selfLink);
            locationDTOSet.add(dto);
        }

        return locationDTOSet;
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationDTO> addLocation(@RequestBody Location location) throws ResourceNotFoundException {
        locationService.addLocation(location);

        Link link = linkTo(methodOn(LocationController.class).getLocation(location.getId())).withSelfRel();

        return new ResponseEntity<>(new LocationDTO(location, link), HttpStatus.OK);
    }

    @PutMapping("/locations/{locationId}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Integer locationId, @RequestBody Location location) throws ResourceNotFoundException {
        locationService.updateLocation(locationId, location);

        Location updatedLocation = locationService.getLocation(locationId);
        Link link = linkTo(methodOn(LocationController.class).getLocation(locationId)).withSelfRel();

        return new ResponseEntity<>(new LocationDTO(updatedLocation, link), HttpStatus.OK);
    }

    @DeleteMapping("/locations/{locationId}")
    public ResponseEntity deleteLocation(@PathVariable Integer locationId) {
        locationService.deleteLocation(locationId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
