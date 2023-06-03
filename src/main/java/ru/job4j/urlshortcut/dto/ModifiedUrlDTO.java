package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Modified url DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifiedUrlDTO {
    /**
     * Modified url
     */
    @NotBlank(message = "Url cannot be blank")
    private String url;
}