package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

/**
 * UrlDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalUrlDTO {
    @NotBlank(message = "Url cannot be blank")
    @URL(message = "Invalid format of URL")
    private String url;
}
