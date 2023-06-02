package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.dto.*;
import ru.job4j.urlshortcut.util.RandomCodeGenerator;

import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Implementation of Site Service interface.
 */
@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService, UserDetailsService {
    /**
     * Site Repository
     */
    private final SiteRepository siteRepository;
    /**
     * Password encoder
     */
    private final BCryptPasswordEncoder encoder;
    /**
     * Length of code used in login and password.
     */
    private static final int CODE_LENGTH = 7;

    /**
     * Save Site.
     * @param siteDTO SiteDTO. Type {@link ru.job4j.urlshortcut.dto.SiteDTO}
     * @return SignedUpDTO
     */
    @Override
    public SignedUpDTO save(SiteDTO siteDTO) {
        validateSiteExists(siteDTO);
        var site = new Site();
        var siteName = siteDTO.getSite();
        site.setSite(siteName);
        var login = siteName + ":" + RandomCodeGenerator.generate(CODE_LENGTH);
        site.setLogin(login);
        var password = siteName + ":" + RandomCodeGenerator.generate(CODE_LENGTH);
        var encoded = encoder.encode(password);
        site.setPassword(encoded);
        siteRepository.save(site);
        return new SignedUpDTO(true, login, password);
    }

    @Override
    public Optional<Site> findByLogin(@NonNull String login) {
        return siteRepository.findByLogin(login);
    }

    @Override
    public Optional<Site> findBySite(@NonNull String site) {
        return siteRepository.findBySite(site);
    }

    private void validateSiteExists(SiteDTO siteDTO) {
        if (findBySite(siteDTO.getSite()).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("%s site name already in use", siteDTO.getSite()));
        }
    }

    /**
     * Load Site by username
     *
     * @param login Username. Type {@link String}
     * @return UserDetails UserDetails. Type {@link UserDetails}
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var site = findByLogin(login);
        if (site.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.get().getLogin(), site.get().getPassword(), emptyList());
    }

}
