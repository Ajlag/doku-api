package org.i4di.doku.repository;


import org.i4di.doku.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByProject_IdAndDeleted(Long projectId, Boolean deleted);

    Optional<Document> findByIdAndProject_Id(Long id, Long projectId);

    Optional<Document> findByTitle(String title);

    Optional<Document> findByUuid(String uuid);

    List<Document> findAllByOrderNumberGreaterThanAndOrderNumberLessThan(Long minRange, Long maxRange);

    @Override
    Optional<Document> findById(Long id);

    @Query(value = "UPDATE document SET title = :title and content=:content WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void updateTitleAndContent(@Param(value = "title") String title, @Param(value = "content") String content, @Param(value = "id") Long id);

    //update query za order
    @Query(value = "UPDATE document SET order_number = :orderNumber WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void updateOrderNumber(@Param(value = "orderNumber") Long orderNumber, @Param(value = "id") Long id);

    @Query(value = "UPDATE document SET status = :status WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void updateStatus(@Param(value = "status") String status, @Param(value = "id") Long id);

    @Query(value = "UPDATE document SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

}
