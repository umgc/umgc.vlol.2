/**
 * User service class.
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

import com.vlol.model.Role;
import com.vlol.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vlol.repository.RoleRepository;
import com.vlol.repository.UserRepository;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(Boolean.TRUE);
        user.setIsLocked(Boolean.FALSE);
        Role userRole = roleRepository.findRoleByTitle("participant");
        // user.setRoles(new HashSet<VLOLRole>(Arrays.asList(userRole)));
        user.setRole(userRole);
        Date date = new Date();
        user.setLastLoginDate(date);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Long id = user.getUserID();
        User u = this.getUser(id);
        user.setPassword(u.getPassword());
        user.setSecurityAnswer(u.getSecurityAnswer());
        user.setUsername(u.getUsername());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(long userID) {
        return userRepository.findById(userID).get();
    }

    public void deleteUser(long userID) {
        userRepository.deleteById(userID);
    }

    public List<User> findUserByKeyword(String keyword) {
        return userRepository.findUserByKeyword(keyword);
    }
}
