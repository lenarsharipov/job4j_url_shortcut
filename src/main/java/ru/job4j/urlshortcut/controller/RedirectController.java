package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.service.UrlService;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

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
    @Transactional
    @GetMapping("/{modifiedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String modifiedUrl) {
        var url = urlService
                .findByModifiedUrl(modifiedUrl)
                .orElseThrow(() ->
                        new NoSuchElementException(modifiedUrl + " url does not exist"));
        urlService.incrementCalls(url.getId());
        return new ResponseEntity<>(
                url.getOriginalUrl(),
                HttpStatus.FOUND
        );
    }

}
