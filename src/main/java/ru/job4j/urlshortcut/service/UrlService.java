package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import ru.job4j.urlshortcut.dto.ModifiedUrlDTO;
import ru.job4j.urlshortcut.dto.OriginalUrlDTO;
import ru.job4j.urlshortcut.dto.StatUrlDTO;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

/**
 * Url service interface
 */
public interface UrlService {
    /**
     * Save passed url.
     * @param originalUrlDTO UrlDTO. Type {@link OriginalUrlDTO}
     * @return Url. Type {@link ru.job4j.urlshortcut.model.Url}
     */
    ModifiedUrlDTO save(@NonNull OriginalUrlDTO originalUrlDTO, String token);

    /**
     * Get list of all persisted urls.
     * @return List of StatUrlDTO. Type {@link  List<ru.job4j.urlshortcut.dto.StatUrlDTO>}
     */
    List<StatUrlDTO> findAll();

    /**
     * Get Optional of found by modifiedUrl Url.
     * @param modifiedUrl Url. Type {@link ru.job4j.urlshortcut.model.Url}
     * @return Optional of Url.
     */
    Optional<Url> findByModifiedUrl(@NonNull String modifiedUrl);

    /**
     * Increment calls counter by +1.
     *
     * @param urlId Url id. Type {@link Integer}
     */
    void incrementCalls(@NonNull Integer urlId);

    Url getOriginalAndIncrement(String modifiedUrl);

}
