package test.vakoms.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import test.vakoms.domain.Role;
import test.vakoms.domain.User;
import test.vakoms.presentation.UserController;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RoleDTO extends ResourceSupport {
    private Role role;

    public RoleDTO(Role role, Link selfLink) {
        this.role = role;
        add(selfLink);
        add(linkTo(methodOn(UserController.class).getUsersByRoleId(role.getId())).withRel("users"));
    }

    public Integer getRoleId() {
        return role.getId();
    }

    public String getRoleName() {
        return role.getName();
    }

    public Set<User> getUsers() {
        return role.getUsers();
    }
}
