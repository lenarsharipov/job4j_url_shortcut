package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.ModifiedUrlDTO;
import ru.job4j.urlshortcut.dto.OriginalUrlDTO;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;

/**
 * Url converter controller.
 */
@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class UrlConverter {

    /**
     * Url Service.
     */
    private final UrlService urlService;

    /**
     * Convert passed original url into short url.
     * @param originalUrlDTO OriginalUrlDTO. Type {@link ru.job4j.urlshortcut.dto.OriginalUrlDTO}
     * @return ResponseEntity of Url.
     */
    @PostMapping("/")
    public ResponseEntity<ModifiedUrlDTO> convertUrl(@Valid @RequestBody OriginalUrlDTO originalUrlDTO,
                                                     @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<>(
                urlService.save(originalUrlDTO, token),
                HttpStatus.CREATED
        );
    }

}
