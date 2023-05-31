package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class SimpleUserDetailsService implements UserDetailsService {
    /**
     * SiteService
     */
    private SiteService siteService;

    /**
     * Load Site by username
     *
     * @param username Username. Type {@link String}
     * @return UserDetails UserDetails. Type {@link UserDetails}
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Site site = siteService.findByUsername(username);
        if (site == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(site.getUsername(), site.getPassword(), emptyList());
    }
}
