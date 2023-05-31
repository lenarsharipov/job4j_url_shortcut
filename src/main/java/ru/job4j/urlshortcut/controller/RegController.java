package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.SignedUpDTO;
import ru.job4j.urlshortcut.dto.SiteDTO;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;

/**
 * Site sign-up controller
 */
@RestController
@RequestMapping("/sites")
@AllArgsConstructor
public class RegController {
    private final SiteService siteService;

    /**
     * Sign-up
     * @param siteDto SiteDTO. Type {@link ru.job4j.urlshortcut.dto.SiteDTO}
     * @return ResponseEntity of SignedUpDTO.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<SignedUpDTO> signUp(@Valid @RequestBody SiteDTO siteDto) {
        return new ResponseEntity<>(
                siteService.save(siteDto),
                HttpStatus.CREATED
        );
    }
}
