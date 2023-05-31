package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.OriginalUrlDTO;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;

@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class UrlConverter {

    private final UrlService urlService;

    /**
     * Convert passed original url into short url.
     * @param originalUrlDTO OriginalUrlDTO. Type {@link ru.job4j.urlshortcut.dto.OriginalUrlDTO}
     * @return ResponseEntity of Url.
     */
    @PostMapping("/")
    public ResponseEntity<Url> convertUrl(@Valid @RequestBody OriginalUrlDTO originalUrlDTO) {
        return new ResponseEntity<>(
                urlService.save(originalUrlDTO),
                HttpStatus.CREATED
        );
    }
}
