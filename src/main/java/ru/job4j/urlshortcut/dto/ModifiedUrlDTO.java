package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * UrlDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifiedUrlDTO {
    @NotBlank(message = "Url cannot be blank")
    private String url;
}