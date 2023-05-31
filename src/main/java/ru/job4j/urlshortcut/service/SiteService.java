package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import ru.job4j.urlshortcut.dto.SignedUpDTO;
import ru.job4j.urlshortcut.dto.SiteDTO;
import ru.job4j.urlshortcut.model.Site;

public interface SiteService {
    SignedUpDTO save(SiteDTO siteDTO);

    Site findByUsername(@NonNull String username);

}
