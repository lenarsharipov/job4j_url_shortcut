package ru.job4j.urlshortcut.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Site;

/**
 * Spring Data Repository for Site.
 */
public interface SiteRepository extends CrudRepository<Site, Long> {
    /**
     * Find site by passed username.
     * @param username Username. Type {@link String}
     * @return Site. Type {@link ru.job4j.urlshortcut.model.Site}
     */
    Site findByUsername(@NonNull String username);
}
