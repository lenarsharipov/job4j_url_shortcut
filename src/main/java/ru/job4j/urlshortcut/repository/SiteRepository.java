package ru.job4j.urlshortcut.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

/**
 * Spring Data Repository for Site.
 */
public interface SiteRepository extends CrudRepository<Site, Long> {
    /**
     * Find site by login.
     * @param login Login. Type {@link String}.
     * @return Optional of Site.
     */
    Optional<Site> findByLogin(@NonNull String login);

    /**
     * Find site by site name.
     * @param site Site. Type {@link String}
     * @return Optional of Site.
     */
    Optional<Site> findBySite(@NonNull String site);
}
