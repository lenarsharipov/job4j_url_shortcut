package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Site Dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO {
    /**
     * Site name
     */
    @NotBlank(message = "Site name cannot be blank, null, empty")
    private String site;
}
