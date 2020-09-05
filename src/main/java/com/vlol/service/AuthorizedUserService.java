/**
 * UserAuthorizedUser service class.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  vlol
 * @package service
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.service;

import com.vlol.model.AuthorizedUser;
import com.vlol.repository.AuthorizedUserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorizedUserService {
    @Autowired
    private final AuthorizedUserRepository authorizedUserRepository;

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

    public void deleteAuthorizedUser(Long medicationID) {
        authorizedUserRepository.deleteByPK(medicationID);
    }
}
