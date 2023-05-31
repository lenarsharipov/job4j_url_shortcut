package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Site model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sites")
public class Site {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /**
     * Web site url
     */
    @NotBlank(message = "Web site url cannot be blank")
    @Column(name = "site", nullable = false)
    private String site;

    /**
     * Username/login
     */
    @EqualsAndHashCode.Include
    @NotBlank(message = "Login cannot be blank")
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Password
     */
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;
}
