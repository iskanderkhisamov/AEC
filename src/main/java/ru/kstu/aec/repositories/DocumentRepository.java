package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT doc_code FROM document WHERE doc_code = ?1", nativeQuery = true)
    String[] findDocumentsDocCode(String token);

    @Query(value = "SELECT id FROM document WHERE doc_code = ?1", nativeQuery = true)
    String[] findDocumentsIdByDocCode(String token);
}
