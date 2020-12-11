package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Document;
import org.i4di.doku.dto.DocumentDTO;
import org.i4di.doku.dto.SharedDocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SharedDocumentMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "content", target = "content")
    SharedDocumentDTO documentToDocumentDTO(Document document);

    List<SharedDocumentDTO> documentsToDocumentDTOs(List<Document> documents);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Document.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "content", target = "content")
    Document documentDTOToDocument(SharedDocumentDTO documentDTO);

    List<Document> documentDTOsToDocuments(List<SharedDocumentDTO> documentDTOs);
}
