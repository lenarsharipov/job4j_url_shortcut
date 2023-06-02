package ru.job4j.urlshortcut.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.urlshortcut.filter.JWTAuthenticationFilter;

import java.util.Base64;

@AllArgsConstructor
@Slf4j
public class TokenUtil {
    private static final String BEARER = JWTAuthenticationFilter.TOKEN_PREFIX;
    private static final String SUB = "sub";
    private static final String DELIMITER = "\\.";

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
