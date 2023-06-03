package ru.job4j.urlshortcut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
     * Unique Site name
     */
    @NotBlank(message = "Web site url cannot be blank")
    @Column(name = "site", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String site;

    /**
     * Unique Login
     */
    @EqualsAndHashCode.Include
    @NotBlank(message = "Login cannot be blank")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    /**
     * Password
     */
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Collection of modified urls associated
     * with unique web-site persisted in DB.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "site",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("site")
    private List<Url> urls = new ArrayList<>();

}
