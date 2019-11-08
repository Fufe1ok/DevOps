package test.vakoms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.vakoms.domain.Role;
import test.vakoms.domain.User;
import test.vakoms.exceptions.ResourceNotFoundException;
import test.vakoms.infrastructure.persistence.RoleRepository;
import test.vakoms.infrastructure.persistence.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    public Role getRole(Integer id) throws ResourceNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with this id is not found"));
    }

    @Transactional
    public void addRole(Role role) {
        String regexp = "\\s*|\\W*";
        if (!role.getName().matches(regexp)) {
            roleRepository.save(role);
        }
    }

    @Transactional
    public void updateRole(Integer id, Role role) {
        Role roleToUpdate = roleRepository.findById(id).get();

        if (!role.getName().isEmpty()) {
            roleToUpdate.setName(role.getName());
        }

        if (!role.getUsers().isEmpty()) {
            roleToUpdate.setUsers(role.getUsers());
        }

        roleRepository.save(roleToUpdate);
    }

    @Transactional
    public void deleteRole(Integer id) {
        Role role = roleRepository.findById(id).get();
        Set<User> users = role.getUsers();
        if (!users.isEmpty()) {
            users.forEach(x -> {
                User user = userRepository.findById(x.getId()).get();
                user.setRole(null);
                userRepository.save(user);
            });
            role.setUsers(new HashSet<>());
        }
        roleRepository.deleteById(id);
    }

}
