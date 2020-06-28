/**
 * String to Role formatter class.
 * See https://stackoverflow.com/questions/25234357/select-tag-with-object-thymeleaf-and-spring-mvc
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
 * @package configuration
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.service;

import com.vlol.model.Role;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

@Service
public class RoleFormatter implements Formatter<Role> {

    @Autowired
    RoleService roleService; //Service -> DB

    @Override
    public String print(Role object, Locale locale) {
        return (object != null ? object.getRoleID().toString() : "");
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Long id = Long.valueOf(text);
        return this.roleService.getRole(id); //return Type object form DB
    }
}
