package org.i4di.doku.api;

import org.i4di.doku.dto.RoleDTO;
import org.i4di.doku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleAPI {

    private RoleService roleService;

    @Autowired
    public RoleAPI(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(roleService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<RoleDTO> byId = roleService.show(id);

        return byId
            .map(roleDTO -> new ResponseEntity<>(roleDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.create(role), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RoleDTO role) {
        role.setId(id);

        Optional<RoleDTO> updated = roleService.update(role);

        return updated
            .map(roleDTO -> new ResponseEntity<>(roleDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> toggleDeleted(@PathVariable(value = "id") Long id) {
        boolean result = roleService.delete(id);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //handle Role's permissions requests
    @PutMapping(value = "/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> addPermission(@PathVariable(value = "roleId") Long roleId, @PathVariable(value = "permissionId") Long permissionId) {
        boolean result = roleService.addPermissions(roleId, permissionId);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> removePermission(@PathVariable(value = "roleId") Long roleId, @PathVariable(value = "permissionId") Long permissionId) {
        boolean result = roleService.removePermission(roleId, permissionId);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
