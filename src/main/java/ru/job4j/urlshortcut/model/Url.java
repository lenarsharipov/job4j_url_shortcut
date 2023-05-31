package ru.job4j.urlshortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.job4j.urlshortcut.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Url model.
 */
@Data
@Entity
@Table(name = "urls")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Url {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Min(value = 1, message = "Id should be a number > 0")
    private Integer id;

    /**
     * Original URL
     */
    @NotBlank(message = "Original Url should not be empty")
    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    /**
     * Shorted, Modified url.
     */
    @NotBlank(message = "Shorted url should not be empty", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    @Column(name = "modified_url", nullable = false)
    private String modifiedUrl;

    /**
     * Url calls counter.
     */
    @Column(name = "calls")
    private int calls;
}
