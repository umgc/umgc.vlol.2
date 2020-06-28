/**
 * Application entry point.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  com
 * @package VLOL
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@SpringBootApplication
public class VlolApplication {

    /**
     * Application entry method.
     *
     * @param args Command-line arguments, if any.
     */
    public static void main(String[] args) {
        SpringApplication.run(VlolApplication.class, args);
    }

}
