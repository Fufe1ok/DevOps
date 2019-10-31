package test.vakoms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.vakoms.domain.Car;
import test.vakoms.domain.Fare;
import test.vakoms.domain.Role;
import test.vakoms.domain.User;
import test.vakoms.exceptions.ResourceNotFoundException;
import test.vakoms.infrastructure.persistence.CarRepository;
import test.vakoms.infrastructure.persistence.FareRepository;
import test.vakoms.infrastructure.persistence.RoleRepository;
import test.vakoms.infrastructure.persistence.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FareRepository fareRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Set<User> getUsersByRoleId(Integer roleId) {
        return new HashSet<>(roleRepository.findById(roleId).get().getUsers());
    }

    public User getUser(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with this id is not found"));
    }

    @Transactional
    public Set<User> findAllUsersByFirstName(String firstName) {
        String regexp = "((\\S|^)*" + firstName + "(\\S|$)*|((\\S|^)*" + firstName.toLowerCase(Locale.ENGLISH)
                + "(\\S|$)*)|((\\S|^)*" + firstName.toUpperCase(Locale.ENGLISH) + "(\\S|$)*))";
        List<User> users = getAllUsers();
        Set<User> foundUsers = new HashSet<>();
        for (User user : users) {
            if (user.getFirstName().matches(regexp)) {
                foundUsers.add(user);
            }
        }

        return foundUsers;
    }

    @Transactional
    public void addUser(User user) {
        String regexp = "\\s*|\\W*";
        if (!user.getFirstName().matches(regexp) && !user.getLastName().matches(regexp) && !user.getEmail().matches(regexp) && !user.getPassword().matches(regexp)) {
            userRepository.save(user);
        }
    }

    @Transactional
    public void addUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).get();
        Role role = roleRepository.findById(roleId).get();
        user.setRole(role);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Integer id, User user) {
        User userToUpdate = userRepository.findById(id).get();

        if (!user.getFirstName().isEmpty()) {
            userToUpdate.setFirstName(user.getFirstName());
        }

        if (!user.getLastName().isEmpty()) {
            userToUpdate.setLastName(user.getLastName());
        }

        if (!user.getEmail().isEmpty()) {
            userToUpdate.setEmail(user.getEmail());
        }

        if (!user.getPassword().isEmpty()) {
            userToUpdate.setPassword(user.getPassword());
        }

        if (user.getRole() != null) {
            userToUpdate.setRole(user.getRole());
        }

        if (user.getCars() != null) {
            userToUpdate.setCars(user.getCars());
        }

        if (!user.getFares().isEmpty()) {
            userToUpdate.setFares(user.getFares());
        }

        userRepository.save(userToUpdate);
    }

    @Transactional
    public void updateUserRole(Integer id, Integer roleId) {
        Role role = roleRepository.findById(roleId).get();
        User userToUpdate = userRepository.findById(id).get();

        userToUpdate.setRole(role);

        userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).get();

        Set<Car> userCars = user.getCars();
        Set<Fare> userFares = user.getFares();

        if (!userCars.isEmpty()) {
            userCars.forEach(x -> {
                Car car = carRepository.findById(x.getId()).get();
                car.setUser(null);
                carRepository.save(car);
            });
            user.setCars(new HashSet<>());
        }

        if (!userFares.isEmpty()) {
            userFares.forEach(x -> {
                Fare fare = fareRepository.findById(x.getId()).get();
                fare.setUser(null);
                fareRepository.save(fare);
            });
            user.setFares(new HashSet<>());
        }

        userRepository.deleteById(id);
    }
}
