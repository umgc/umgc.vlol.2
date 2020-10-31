/**
 * API Keys service class.
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

import com.vlol.model.APIKeys;
import com.vlol.repository.APIKeysRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class APIKeysService {

  @Autowired private final APIKeysRepository apiKeyRepository;

  @Autowired
  public APIKeysService(APIKeysRepository apiKeyRepository) {
    this.apiKeyRepository = apiKeyRepository;
  }

  public void saveApiKey(APIKeys apiKey) {
    apiKeyRepository.save(apiKey);
  }

  public APIKeys getAPIKey(String apiKey) {
    if (apiKey != null)
      apiKey = apiKey.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
    return apiKeyRepository.findById(apiKey).orElse(null);
  }

  public List<APIKeys> getAllAPIKeys() {
    return apiKeyRepository.findAll();
  }

  public void deleteAPIKey(String apiKey) {
    apiKeyRepository.deleteByPK(apiKey);
  }
}
