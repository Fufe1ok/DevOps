package test.vakoms.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.vakoms.domain.Car;
import test.vakoms.domain.Role;
import test.vakoms.domain.User;
import test.vakoms.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private RoleService roleService;

    private List<User> userList;
    private List<Car> carList;
    private List<Role> roleList;

    private User testUser = new User();

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<>();
        carList = new ArrayList<>();
        roleList = new ArrayList<>();

        roleList.add(new Role("ADMIN"));
        roleList.add(new Role("PRIVILEGED_USER"));

        userList.add(new User("admin0", "admin0", "admin0@gmail.com", "admin0", roleList.get(0)));
        userList.add(new User("admin1", "admin1","admin1@gmail.com", "admin1", roleList.get(0)));
        userList.add(new User("user0", "user0", "user0@gmail.com", "user0", roleList.get(1)));
        userList.add(new User("user0", "user0", "user0@gmail.com", "user0", roleList.get(1)));

        carList.add(new Car("Samsung1", "IMEI001", "Android", "Red", userList.get(0), null));
        carList.add(new Car("Samsung2", "IMEI002", "Android", "Yellow", userList.get(1), null));
        carList.add(new Car("Samsung3", "IMEI003", "Android", "Brown", userList.get(2), null));
        carList.add(new Car("Samsung4", "IMEI004", "Android", "Red", userList.get(3), null));

        for (Role role : roleList) {
            roleService.addRole(role);
        }

        for (User user : userList) {
            userService.addUser(user);
        }

        for (Car car : carList) {
            carService.addCar(car);
        }

    }

    @AfterEach
    public void wrapUp() {
        for (Role role : roleService.getAllRoles()) {
            roleService.deleteRole(role.getId());
        }

        for (Car car : carService.getAllCars()) {
            carService.deleteCar(car.getId());
        }

        for (User user : userService.getAllUsers()) {
            userService.deleteUser(user.getId());
        }
    }

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        assertEquals(users.size(), userList.size());

        for (int i = 0; i < userList.size(); i++) {
            assertEquals(users.get(i).getFirstName(), userList.get(i).getFirstName());
            assertEquals(users.get(i).getEmail(), userList.get(i).getEmail());
            assertEquals(users.get(i).getPassword(), userList.get(i).getPassword());
            assertEquals(users.get(i).getRole().getName(), userList.get(i).getRole().getName());
        }
    }

    @Test
    void getUsersByRoleId() {
        Role role = roleService.getAllRoles().get(0);
        Integer roleId = role.getId();

        for (User user : userService.getUsersByRoleId(roleId)) {
            assertEquals(user.getRole().getName(), role.getName());
        }
    }

    @Test
    void addUser() {
        userService.addUser(testUser);
        assertEquals(userService.getAllUsers().size(), userList.size() + 1);
    }

    @Test
    void addUserRole() throws ResourceNotFoundException {
        userService.addUser(testUser);
        List<User> usersList = userService.getAllUsers();
        User userTest = usersList.get(usersList.size() - 1);
        assertEquals(testUser.getRole(), userTest.getRole());
        userService.addUserRole(userTest.getId(), roleService.getAllRoles().get(0).getId());
        assertEquals(userService.getUser(userTest.getId()).getRole().getName(), roleService.getAllRoles().get(0).getName());
    }

    @Test
    void updateUser() throws ResourceNotFoundException {
        userService.addUser(testUser);
        List<User> usersList = userService.getAllUsers();

        User userTest = usersList.get(usersList.size() - 1);
        assertNotEquals(userTest.getFirstName(), usersList.get(0).getFirstName());

        userService.updateUser(userTest.getId(), usersList.get(0));

        userTest = userService.getUser(userTest.getId());
        assertEquals(userTest.getFirstName(), usersList.get(0).getFirstName());
        assertEquals(userTest.getEmail(), usersList.get(0).getEmail());
        assertEquals(userTest.getPassword(), usersList.get(0).getPassword());
    }

    @Test
    void updateUserRole() throws ResourceNotFoundException {
        userService.addUser(testUser);
        List<User> usersList = userService.getAllUsers();
        User userTest = usersList.get(usersList.size() - 1);
        assertEquals(testUser.getRole(), userTest.getRole());

        userService.addUserRole(userTest.getId(), roleService.getAllRoles().get(0).getId());
        assertEquals(userService.getUser(userTest.getId()).getRole().getName(), roleService.getAllRoles().get(0).getName());

        userService.updateUserRole(userTest.getId(), roleService.getAllRoles().get(1).getId());
        assertEquals(userService.getUser(userTest.getId()).getRole().getName(), roleService.getAllRoles().get(1).getName());
    }

    @Test
    void deleteUser() {
        int devicesNumber = carService.getAllCars().size();
        List<Integer> integers = new ArrayList<>();

        for (User user : userService.getAllUsers()) {
            integers.add(user.getId());
        }

        for (Integer integer : integers) {
            userService.deleteUser(integer);
        }

        assertEquals(userService.getAllUsers().size(), 0);
        assertEquals(carService.getAllCars().size(), devicesNumber);
    }
}
