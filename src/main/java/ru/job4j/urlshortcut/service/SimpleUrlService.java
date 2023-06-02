package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.ModifiedUrlDTO;
import ru.job4j.urlshortcut.dto.OriginalUrlDTO;
import ru.job4j.urlshortcut.dto.StatUrlDTO;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.util.RandomCodeGenerator;
import ru.job4j.urlshortcut.util.TokenUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final SiteRepository siteRepository;

    /**
     * Convert passed urlDTO into Url and Save.
     * @param originalUrlDTO UrlDTO. Type {@link ru.job4j.urlshortcut.dto.OriginalUrlDTO}
     * @return Url. Type {@link ru.job4j.urlshortcut.model.Url}
     */
    @Override
    public ModifiedUrlDTO save(@NonNull OriginalUrlDTO originalUrlDTO, String token) {
        var login = TokenUtil.getLogin(token);
        if (siteRepository.findByLogin(login).isEmpty()) {
            throw new IllegalArgumentException("Login not found");
        }
        var url = new Url();
        url.setSite(siteRepository.findByLogin(login).get());
        url.setOriginalUrl(originalUrlDTO.getUrl());
        var modifiedUrl = RandomCodeGenerator.generate(URL_LENGTH);
        url.setModifiedUrl(modifiedUrl);
        urlRepository.save(url);

        return new ModifiedUrlDTO(modifiedUrl);
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

    @Transactional
    @Override
    public Url getOriginalAndIncrement(String modifiedUrl) {
        var url = findByModifiedUrl(modifiedUrl);
        if (url.isEmpty()) {
            throw new NoSuchElementException(modifiedUrl + " url does not exist");
        }
        incrementCalls(url.get().getId());
        return url.get();
    }

}
