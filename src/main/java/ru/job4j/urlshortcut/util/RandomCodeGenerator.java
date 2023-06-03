package ru.job4j.urlshortcut.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Code generator
 */
public class RandomCodeGenerator {
    /**
     * Line
     */
    private static final String LINE =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Generate unique secure code of passed length value.
     * @param length Length.
     * @return generated unique code of specified length.
     */
    public static String generate(int length) {
        var secureRandom = new SecureRandom();
        return IntStream.range(0, length)
                .map(i -> secureRandom.nextInt(LINE.length()))
                .mapToObj(randomIndex -> String.valueOf(LINE.charAt(randomIndex)))
                .collect(Collectors.joining());
    }
}
