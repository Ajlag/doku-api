package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Document;
import org.i4di.doku.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "status", target = "status")
    DocumentDTO documentToDocumentDTO(Document document);

    List<DocumentDTO> documentsToDocumentDTOs(List<Document> documents);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "status", target = "status")
    Document documentDTOToDocument(DocumentDTO documentDTO);

    List<Document> documentDTOsToDocuments(List<DocumentDTO> documentDTOs);

}
