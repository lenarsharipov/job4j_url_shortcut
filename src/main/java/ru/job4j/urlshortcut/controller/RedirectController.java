package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.service.UrlService;

@RestController
@RequestMapping("/redirects")
@AllArgsConstructor
public class RedirectController {

    private final UrlService urlService;

    /**
     * Get original url.
     * @param modifiedUrl String. Type {@link String}.
     * @return ResponseEntity.
     */
    @GetMapping("/{modifiedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String modifiedUrl) {
        return new ResponseEntity<>(
                urlService.getOriginalAndIncrement(modifiedUrl)
                        .getOriginalUrl(),
                HttpStatus.FOUND
        );
    }

}
