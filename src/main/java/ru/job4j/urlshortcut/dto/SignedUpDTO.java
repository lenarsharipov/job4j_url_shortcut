package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto used for sign-up operation.
 * Includes registration status (true/false),
 * generated login and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignedUpDTO {
    /**
     * Registration status.
     */
    private boolean registration;

    /**
     * Generated login
     */
    private String login;

    /**
     * Generated password
     */
    private String password;

}
