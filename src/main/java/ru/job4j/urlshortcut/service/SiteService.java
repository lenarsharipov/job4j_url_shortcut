package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import ru.job4j.urlshortcut.dto.SignedUpDTO;
import ru.job4j.urlshortcut.dto.SiteDTO;
import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

/**
 * Site service interface.
 */
public interface SiteService {
    /**
     * Save
     */
    SignedUpDTO save(SiteDTO siteDTO);

    /**
     * Find by Login
     */
    Optional<Site> findByLogin(@NonNull String login);

    /**
     * Find by site name
     */
    Optional<Site> findBySite(@NonNull String site);

}
