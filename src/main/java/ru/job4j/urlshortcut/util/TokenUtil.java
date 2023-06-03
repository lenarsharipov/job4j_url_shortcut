package ru.job4j.urlshortcut.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.urlshortcut.filter.JWTAuthenticationFilter;

import java.util.Base64;

/**
 * Token utility class
 */
@AllArgsConstructor
@Slf4j
public class TokenUtil {
    /**
     * Token starting prefix.
     */
    private static final String BEARER = JWTAuthenticationFilter.TOKEN_PREFIX;

    /**
     * Field sub located in decoded payload part of token.
     */
    private static final String SUB = "sub";

    /**
     * Delimiter
     */
    private static final String DELIMITER = "\\.";

    /**
     * Get login from valid passed encoded JWT.
     * @param token String. Type {@link java.lang.String}
     * @return login in String format.
     */
    public static String getLogin(String token) {
        String[] parts = token.replaceAll(BEARER, "").split(DELIMITER);
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            log.error("UNABLE TO RETRIEVE LOGIN", e);
            throw new IllegalArgumentException("UNABLE TO RETRIEVE LOGIN");
        }
        return rootNode.get(SUB).asText();
    }

}
