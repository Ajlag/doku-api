package org.i4di.doku.api;

import org.i4di.doku.dto.ProjectDTO;
import org.i4di.doku.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectAPI {

    private ProjectService projectService;

    @Autowired
    public ProjectAPI(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(projectService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<ProjectDTO> byId = projectService.show(id);

        return byId
            .map(projectDTO -> new ResponseEntity<>(projectDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjectDTO project) {
        return new ResponseEntity<>(projectService.create(project), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ProjectDTO project) {
        project.setId(id);

        Optional<ProjectDTO> updated = projectService.update(project);

        return updated
            .map(projectDTO -> new ResponseEntity<>(projectDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> toggleDeleted(@PathVariable(value = "id") Long id) {
        boolean result = projectService.delete(id);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //handle Role's permissions requests
    @PutMapping(value = "/{projectId}/users/{userId}")
    public ResponseEntity<?> addUser(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "userId") Long userId) {
        boolean result = projectService.addUser(projectId, userId);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/{projectId}/users/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "userId") Long userId) {
        boolean result = projectService.removeUser(projectId, userId);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
