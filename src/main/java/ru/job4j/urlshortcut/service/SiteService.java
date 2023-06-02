package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import ru.job4j.urlshortcut.dto.SignedUpDTO;
import ru.job4j.urlshortcut.dto.SiteDTO;
import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

public interface SiteService {
    SignedUpDTO save(SiteDTO siteDTO);

    Optional<Site> findByLogin(@NonNull String login);

    Optional<Site> findBySite(@NonNull String site);

}
