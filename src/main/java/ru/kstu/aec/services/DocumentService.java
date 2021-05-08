package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Document;
import ru.kstu.aec.repositories.DocumentRepository;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public String[] loadDocumentsByToken(String token) {
        return documentRepository.findDocumentsDocCode(token);
    }

    public String[] loadDocumentsIdByToken(String token) {
        return documentRepository.findDocumentsIdByDocCode(token);
    }

    public void changeIsTaken(String token, boolean cond, int id) {
        Document document = documentRepository.getOne(Integer.parseInt(documentRepository.findDocumentsIdByDocCode(token)[0]));
        System.out.println("Document ID:" + document.getId());
        System.out.println("Document Code:" + document.getDocCode());
        document.setTeacherId(id);
        document.setTaken(cond);
        System.out.println("isTaken: " + document.isTaken());
        System.out.println("Document Teacher ID:" + document.getTeacherId());
        documentRepository.save(document);
    }

    @Transactional
    public void createDocument(Document document) {
        documentRepository.save(document);
    }
}
