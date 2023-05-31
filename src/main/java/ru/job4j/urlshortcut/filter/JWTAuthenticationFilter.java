package ru.job4j.urlshortcut.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.job4j.urlshortcut.model.Site;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/**
 * JWTAuthentication filter
 * Authenticate users
 */
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Secret key
     */
    public static final String SECRET = "SecretKeyToGenJWTs";

    /**
     * Expiration time (10 days == 864_000_000 ms)
     */
    public static final long EXPIRATION_TIME = 864_000_000;

    /**
     * Token prefix
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Authorization header
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * Sign up url
     */
    public static final String SIGN_UP_URL = "/sites/sign-up";

    /**
     * AuthenticationManager
     */
    private AuthenticationManager auth;

    /**
     * Check whether login and password correct.
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @return Authentication
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            Site creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Site.class);

            return auth.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates token
     * @param req   HttpServletRequest
     * @param res   HttpServletResponse
     * @param chain FilterChain
     * @param auth  Authentication
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

}
