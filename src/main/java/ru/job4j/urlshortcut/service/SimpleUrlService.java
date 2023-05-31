package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.job4j.urlshortcut.dto.OriginalUrlDTO;
import ru.job4j.urlshortcut.dto.StatUrlDTO;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.*;
import ru.job4j.urlshortcut.util.RandomCodeGenerator;

/**
 * UrlService interface implementation.
 */
@Service
@AllArgsConstructor
public class SimpleUrlService implements UrlService {
    /**
     * Length value of shortened url.
     */
    private static final int URL_LENGTH = 10;
    /**
     * Url repository
     */
    private final UrlRepository urlRepository;

    /**
     * Convert passed urlDTO into Url and Save.
     * @param originalUrlDTO UrlDTO. Type {@link ru.job4j.urlshortcut.dto.OriginalUrlDTO}
     * @return Url. Type {@link ru.job4j.urlshortcut.model.Url}
     */
    @Override
    public Url save(@NonNull OriginalUrlDTO originalUrlDTO) {
        var url = new Url();
        url.setOriginalUrl(originalUrlDTO.getUrl());
        var modifiedUrl = RandomCodeGenerator.generate(URL_LENGTH);
        url.setModifiedUrl(modifiedUrl);
        return urlRepository.save(url);
    }

    /**
     * Get list of persisted Urls.
     * @return List of StatUrlDTO. Type {@link List<ru.job4j.urlshortcut.dto.StatUrlDTO>}
     */
    @Override
    public List<StatUrlDTO> findAll() {
        return urlRepository.findAll()
                .stream()
                .map(url -> new StatUrlDTO(
                        url.getOriginalUrl(),
                        url.getCalls()))
                .collect(Collectors.toList());
    }

    /**
     * Get Optional of url found by short url.
     * @param modifiedUrl Url. Type {@link ru.job4j.urlshortcut.model.Url}
     * @return Optional of Url. Type {@link ru.job4j.urlshortcut.model.Url}
     */
    @Override
    public Optional<Url> findByModifiedUrl(@NonNull String modifiedUrl) {
        return urlRepository.findByModifiedUrl(modifiedUrl);
    }

    /**
     * Get incremented value.
     *
     * @param urlId Url id. Type {@link Integer}
     */
    @Override
    public void incrementCalls(@NonNull Integer urlId) {
        urlRepository.incrementCalls(urlId);
    }
}
