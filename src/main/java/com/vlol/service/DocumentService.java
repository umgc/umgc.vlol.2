/**
 * Document service class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package service
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.service;

import com.vlol.model.Document;
import com.vlol.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentService {

  @Autowired private final DocumentRepository documentRepository;

  @Autowired
  public DocumentService(DocumentRepository documentRepository) {
    this.documentRepository = documentRepository;
  }

  public void saveDocument(Document document) {
    documentRepository.save(document);
  }

  public Document getDocument(Long documentId) {
    return documentRepository.findById(documentId).orElse(null);
  }

  public void deleteDocument(Long documentId) {
    documentRepository.deleteByPK(documentId);
  }
}
