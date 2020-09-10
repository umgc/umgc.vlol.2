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

import com.vlol.model.Condition;
import com.vlol.model.Role;
import com.vlol.model.User;
import com.vlol.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vlol.repository.RoleRepository;
import com.vlol.repository.UserInfoRepository;
import com.vlol.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service("userService")
@Transactional
public class UserService {

    private Validator validator;
    @Autowired
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserInfoRepository userInfoRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    public Set<ConstraintViolation<User>> isValid(User user){
        return validator.validate(user);
    }
    
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public UserInfo updateUserInfo(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }
    public User updateUser(User user) {
        return updateUser(user, false);
    }
    public User updateUser(User user, Boolean updatePassword) {
        Long id = user.getUserId();
        User u = this.getUser(id);
        if(updatePassword)
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        else
            user.setPassword(u.getPassword());
        //user.setSecurityAnswer(u.getSecurityAnswer());
        user.setEmail(u.getEmail());
        return userRepository.save(user);
    }

    public User userLogin(String email) {
        User user = this.findUserByEmail(email);
        Date date = new Date();
        user.setLastLoginDate(date);
        userRepository.save(user);
        return user;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> getAllParticipants() {
        return userRepository.findAllParticipants();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findUserByKeyword(String keyword) {
        return userRepository.findUserByKeyword(keyword);
    }
    public List<User> findAuthorizingUsers(String email) {
        return userRepository.findAuthorizingUsers(email);
    }
}
