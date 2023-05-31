package ru.job4j.urlshortcut.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for Url.
 */
public interface UrlRepository extends CrudRepository<Url, Long> {
    /**
     * Find all urls.
     * @return List of Urls. Type {@link List<ru.job4j.urlshortcut.model.Url>}
     */
    List<Url> findAll();

    /**
     * Find url by modified one.
     * @param modifiedUrl Shortened url. Type {@link String}
     * @return Optional of Url. Type {@link Optional<ru.job4j.urlshortcut.model.Url>}
     */
    Optional<Url> findByModifiedUrl(@NonNull String modifiedUrl);

    /**
     * Increment Call by +1
     * @param urlId UrlId. Type{@link Integer}
     * @return int.
     */
    @Modifying
    @Query(value = """
            UPDATE urls
            SET calls = calls + 1
            WHERE id = ?1
            """, nativeQuery = true)
    int incrementCalls(Integer urlId);

}
