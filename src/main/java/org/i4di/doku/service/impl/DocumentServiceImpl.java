package org.i4di.doku.service.impl;

import org.i4di.doku.domain.Document;
import org.i4di.doku.domain.Project;
import org.i4di.doku.dto.DocumentDTO;
import org.i4di.doku.dto.SharedDocumentDTO;
import org.i4di.doku.dto.mapper.DocumentMapper;
import org.i4di.doku.dto.mapper.SharedDocumentMapper;
import org.i4di.doku.repository.DocumentRepository;
import org.i4di.doku.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;
    private SharedDocumentMapper sharedDocumentMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, SharedDocumentMapper sharedDocumentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.sharedDocumentMapper = sharedDocumentMapper;
    }

    @Override
    public List<DocumentDTO> listAll(Long projectId) {
        return documentMapper.documentsToDocumentDTOs(
            documentRepository.findAllByProject_IdAndDeleted(projectId, false)
        );
    }

    @Override
    public Optional<DocumentDTO> show(Long documentId, Long projectId) {
        Optional<Document> byId = documentRepository.findByIdAndProject_Id(documentId, projectId);

        return byId.map(document -> documentMapper.documentToDocumentDTO(document));
    }

    @Override
    public Optional<DocumentDTO> show(String title) {
        Optional<Document> byTitle = documentRepository.findByTitle(title);

        return byTitle.map(document -> documentMapper.documentToDocumentDTO(document));
    }

    @Override
    public Optional<SharedDocumentDTO> showByUUID(String uuid) {
        Optional<Document> byUUID = documentRepository.findByUuid(uuid);

        return byUUID.map(document -> sharedDocumentMapper.documentToDocumentDTO(document));
    }

    @Override
    public Optional<DocumentDTO> create(DocumentDTO document, Long projectId) {
        Document toCreate = documentMapper.documentDTOToDocument(document);

        Project proj = new Project();
        proj.setId(projectId);

        toCreate.setProject(proj);
        toCreate.setUuid(UUID.randomUUID().toString());

        return Optional.of(documentMapper.documentToDocumentDTO(
            documentRepository.save(toCreate)
        ));
    }

    @Override
    public Optional<DocumentDTO> update(DocumentDTO document) {
        Optional<Document> byId = documentRepository.findById(document.getId());

        if (!byId.isPresent()) {
            return Optional.empty();
        }
        Document updated = documentMapper.documentDTOToDocument(document);
        updated.setOrderNumber(byId.get().getOrderNumber());
        updated.setStatus(byId.get().getStatus());
        updated.setProject(byId.get().getProject());

        return Optional.of(documentMapper.documentToDocumentDTO(
            documentRepository.save(updated)
        ));
    }


    @Override
    public Optional<DocumentDTO> updateOrderNumber(Long ID, Long orderNumber) {
        Optional<Document> byId = documentRepository.findById(ID);

        if (!byId.isPresent()) {
            return Optional.empty();
        }

        if (byId.get().getOrderNumber().equals(orderNumber)) {
            return Optional.empty();
        }

        List<Document> modifyingDocs;
        Long minRange = orderNumber - 1, maxRange = byId.get().getOrderNumber(), additionValue = 1L;

        if (byId.get().getOrderNumber() < orderNumber) {
            minRange = byId.get().getOrderNumber();
            maxRange = orderNumber + 1;
            additionValue = -1L;
        }

        modifyingDocs = documentRepository.findAllByOrderNumberGreaterThanAndOrderNumberLessThan(
            minRange, maxRange);

        for (Document modifyingDoc : modifyingDocs) {
            Long modifiedOrderNo = modifyingDoc.getOrderNumber() + additionValue;

            modifyingDoc.setOrderNumber(modifiedOrderNo);
            documentRepository.save(modifyingDoc);

            byId.get().setOrderNumber(orderNumber);
            documentRepository.save(byId.get());
        }

        return Optional.of(documentMapper.documentToDocumentDTO(byId.get()));
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        Optional<Document> byId = documentRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        documentRepository.updateStatus(status, id);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Document> byId = documentRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        documentRepository.delete(id);

        return true;
    }

}
