/**
 * UserAuthorizedUser service class.
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

import com.vlol.model.AuthorizedUser;
import com.vlol.repository.AuthorizedUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorizedUserService {
  @Autowired private final AuthorizedUserRepository authorizedUserRepository;

  @Autowired
  public AuthorizedUserService(AuthorizedUserRepository authorizedUserRepository) {
    this.authorizedUserRepository = authorizedUserRepository;
  }

  public List<AuthorizedUser> getAllAuthorizedUsers() {
    return authorizedUserRepository.findAll();
  }

  public void saveAuthorizedUser(AuthorizedUser authorizedUser) {
    authorizedUserRepository.save(authorizedUser);
  }

  public AuthorizedUser getAuthorizedUser(Long authorizedUserId) {
    return authorizedUserRepository.findById(authorizedUserId).get();
  }

  public void deleteAuthorizedUser(Long medicationId) {
    authorizedUserRepository.deleteByPK(medicationId);
  }
}
