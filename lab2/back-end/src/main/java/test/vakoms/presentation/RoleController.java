package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.vakoms.application.RoleService;
import test.vakoms.domain.Role;
import test.vakoms.dto.RoleDTO;
import test.vakoms.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "https://lab2front-256619.appspot.com")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        Link link = linkTo(methodOn(RoleController.class).getAllRoles()).withSelfRel();

        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role entity : roles) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            RoleDTO dto = new RoleDTO(entity, selfLink);
            roleDTOList.add(dto);
        }

        return roleDTOList;
    }

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Integer roleId) throws ResourceNotFoundException {
        Link link = linkTo(methodOn(RoleController.class).getRole(roleId)).withSelfRel();
        return ResponseEntity.ok().body(new RoleDTO(roleService.getRole(roleId), link));
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> addRole(@RequestBody Role role) throws ResourceNotFoundException {
        roleService.addRole(role);

        Link link = linkTo(methodOn(RoleController.class).getRole(role.getId())).withSelfRel();

        return new ResponseEntity<>(new RoleDTO(role, link), HttpStatus.OK);
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Integer roleId, @RequestBody Role role) throws ResourceNotFoundException {
        roleService.updateRole(roleId, role);

        Role updatedRole = roleService.getRole(roleId);
        Link link = linkTo(methodOn(RoleController.class).getRole(roleId)).withSelfRel();

        return new ResponseEntity<>(new RoleDTO(updatedRole, link), HttpStatus.OK);
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
