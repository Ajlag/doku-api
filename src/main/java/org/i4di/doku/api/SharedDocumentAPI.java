package org.i4di.doku.api;

import org.i4di.doku.dto.DocumentDTO;
import org.i4di.doku.dto.SharedDocumentDTO;
import org.i4di.doku.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class SharedDocumentAPI {

    private DocumentService documentService;

    @Autowired
    public SharedDocumentAPI(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "/{docId}")
    public ResponseEntity<?> show(@PathVariable(value = "docId") String documentId) {
        Optional<SharedDocumentDTO> byUUID = documentService.showByUUID(documentId);

        return byUUID
            .map(documentDTO -> new ResponseEntity<>(documentDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
