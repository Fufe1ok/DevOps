package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.vakoms.application.UserService;
import test.vakoms.domain.User;
import test.vakoms.dto.UserDTO;
import test.vakoms.exceptions.ResourceNotFoundException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "https://lab2-frontend.appspot.com")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        Link link = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();

        List<UserDTO> userDTOList = new ArrayList<>();
        for (User entity : users) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            UserDTO dto = new UserDTO(entity, selfLink);
            userDTOList.add(dto);
        }

        return userDTOList;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        Link link = linkTo(methodOn(UserController.class).getUser(userId)).withSelfRel();

        return ResponseEntity.ok().body(new UserDTO(userService.getUser(userId), link));
    }

    @GetMapping(value = "/roles/{roleId}/users")
    public ResponseEntity<Set<UserDTO>> getUsersByRoleId(@PathVariable Integer roleId) {
        Set<User> users = userService.getUsersByRoleId(roleId);

        Link link = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();

        Set<UserDTO> userSet = new HashSet<>();
        for (User entity : users) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            UserDTO dto = new UserDTO(entity, selfLink);
            userSet.add(dto);
        }

        return new ResponseEntity<>(userSet, HttpStatus.OK);
    }

    @GetMapping("/search/users/{firstName}")
    public Set<UserDTO> findAllUsersByFirstName(@PathVariable String firstName) {
        Set<User> users = userService.findAllUsersByFirstName(firstName);
        Link link = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();

        Set<UserDTO> userDTOList = new HashSet<>();
        for (User entity : users) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            UserDTO dto = new UserDTO(entity, selfLink);
            userDTOList.add(dto);
        }

        return userDTOList;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) throws ResourceNotFoundException {
        userService.addUser(user);

        Link link = linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel();

        return new ResponseEntity<>(new UserDTO(user, link), HttpStatus.OK);
    }

    @PostMapping("/roles/{roleId}/users/{userId}")
    public ResponseEntity<UserDTO> addUserRole(@PathVariable Integer userId, @PathVariable Integer roleId) throws ResourceNotFoundException {
        userService.addUserRole(userId, roleId);

        Link link = linkTo(methodOn(UserController.class).getUser(userId)).withSelfRel();

        return new ResponseEntity<>(new UserDTO(userService.getUser(userId), link), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody User user) throws ResourceNotFoundException {
        userService.updateUser(userId, user);

        User updatedUser = userService.getUser(userId);
        Link link = linkTo(methodOn(UserController.class).getUser(userId)).withSelfRel();

        return new ResponseEntity<>(new UserDTO(updatedUser, link), HttpStatus.OK);
    }

    @PutMapping("/roles/{roleId}/users/{userId}")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable Integer userId, @PathVariable Integer roleId) throws ResourceNotFoundException {
        userService.updateUserRole(userId, roleId);

        User updatedUser = userService.getUser(userId);
        Link link = linkTo(methodOn(UserController.class).getUser(userId)).withSelfRel();

        return new ResponseEntity<>(new UserDTO(updatedUser, link), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
