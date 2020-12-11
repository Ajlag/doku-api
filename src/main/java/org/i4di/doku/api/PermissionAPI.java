package org.i4di.doku.api;

import org.i4di.doku.dto.PermissionDTO;
import org.i4di.doku.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionAPI {

    private PermissionService permissionService;

    @Autowired
    public PermissionAPI(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(permissionService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<PermissionDTO> byId = permissionService.show(id);

        return byId
            .map(permissionDTO -> new ResponseEntity<>(permissionDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PermissionDTO permissionDTO) {
        return new ResponseEntity<>(permissionService.create(permissionDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermissionDTO permission) {
        permission.setId(id);

        Optional<PermissionDTO> updated = permissionService.update(permission);

        return updated
            .map(permissionDTO -> new ResponseEntity<>(permissionDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> toggleDeleted(@PathVariable(value = "id") Long id) {
        boolean result = permissionService.delete(id);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
