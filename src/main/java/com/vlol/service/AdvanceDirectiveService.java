/**
 * AdvanceDirective service class.
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
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.service;

import com.vlol.model.AdvanceDirective;
import com.vlol.repository.AdvanceDirectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvanceDirectiveService {

    @Autowired
    private final AdvanceDirectiveRepository advanceDirectiveRepository;

    @Autowired
    public AdvanceDirectiveService(AdvanceDirectiveRepository advanceDirectiveRepository) {
        this.advanceDirectiveRepository = advanceDirectiveRepository;
    }

    public void saveAdvanceDirective(AdvanceDirective advanceDirective) {
        advanceDirectiveRepository.save(advanceDirective);
    }

    public AdvanceDirective getAdvanceDirective(Long advanceDirectiveId) {
        return advanceDirectiveRepository.findById(advanceDirectiveId).orElse(null);
    }

    public void deleteAdvanceDirective(Long advanceDirectiveId) {
        advanceDirectiveRepository.deleteByPK(advanceDirectiveId);
    }
}
