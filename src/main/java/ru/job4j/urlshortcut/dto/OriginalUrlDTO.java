package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

/**
 * Original url DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalUrlDTO {
    /**
     * Original (long) url.
     */
    @NotBlank(message = "Url cannot be blank")
    @URL(message = "Invalid format of URL")
    private String url;
}
