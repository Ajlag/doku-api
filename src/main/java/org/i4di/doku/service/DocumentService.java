package org.i4di.doku.service;

import org.i4di.doku.dto.DocumentDTO;
import org.i4di.doku.dto.SharedDocumentDTO;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<DocumentDTO> listAll(Long projectId);

    Optional<DocumentDTO> show(Long documentId, Long projectId);

    Optional<DocumentDTO> show(String title);

    Optional<SharedDocumentDTO> showByUUID(String uuid);

    Optional<DocumentDTO> create(DocumentDTO user, Long projectId);

    Optional<DocumentDTO> update(DocumentDTO document);

    Optional<DocumentDTO> updateOrderNumber(Long ID, Long orderNumber);

    boolean updateStatus(Long id, String status);

    boolean delete(Long id);
}
