package org.i4di.doku.api;

import org.i4di.doku.dto.*;
import org.i4di.doku.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/documents")
public class DocumentAPI {

    private DocumentService documentService;

    @Autowired
    public DocumentAPI(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable(value = "projectId") Long projectId) {
        return new ResponseEntity<>(documentService.listAll(projectId), HttpStatus.OK);
    }

    @GetMapping(value = "/{docId}")
    public ResponseEntity<?> show(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "docId") Long documentId) {
        Optional<DocumentDTO> byId = documentService.show(documentId, projectId);

        return byId
            .map(projectDTO -> new ResponseEntity<>(projectDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable(value = "projectId") Long projectId, @Valid @RequestBody DocumentDTO document) {
        return new ResponseEntity<>(documentService.create(document, projectId), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{docId}")
    public ResponseEntity<?> update(@PathVariable(value = "docId") Long docId, @RequestBody DocumentDTO document) {
        document.setId(docId);

        Optional<DocumentDTO> updated = documentService.update(document);

        return updated
            .map(documentDTO -> new ResponseEntity<>(documentDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(value = "/{docId}/modifyOrder")
    public ResponseEntity<?> updateOrderNo(@PathVariable(value = "docId") Long docId, @Valid @RequestBody OrderDocumentDTO document) {
        Optional<DocumentDTO> updated = documentService.updateOrderNumber(docId, document.getOrderNumber());

        return updated
            .map(documentDTO -> new ResponseEntity<>(documentDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(value = "/{docId}/modifyStatus")
    public ResponseEntity<?> updateStatus(@PathVariable(value = "docId") Long docId, @Valid @RequestBody StatusDocumentDTO document) {
        boolean result = documentService.updateStatus(docId, document.getStatus());

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{docId}")
    public ResponseEntity<?> delete(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "docId") Long docId) {
        boolean result = documentService.delete(docId);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
