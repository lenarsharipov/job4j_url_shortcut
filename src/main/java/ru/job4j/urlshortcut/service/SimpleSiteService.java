package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.dto.*;
import ru.job4j.urlshortcut.util.RandomCodeGenerator;

/**
 * Implementation of Site Service interface.
 */
@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {
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
        var site = new Site();
        var siteName = siteDTO.getSite();
        site.setSite(siteName);
        var login = siteName + ":" + RandomCodeGenerator.generate(CODE_LENGTH);
        site.setUsername(login);
        var password = siteName + ":" + RandomCodeGenerator.generate(CODE_LENGTH);
        var encoded = encoder.encode(password);
        site.setPassword(encoded);
        siteRepository.save(site);
        return new SignedUpDTO(true, login, password);
    }

    @Override
    public Site findByUsername(@NonNull String username) {
        return siteRepository.findByUsername(username);
    }

}
