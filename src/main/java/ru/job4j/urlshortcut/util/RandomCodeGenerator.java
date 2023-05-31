package ru.job4j.urlshortcut.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCodeGenerator {
    private static final String LINE =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generate(int size) {
        var secureRandom = new SecureRandom();
        return IntStream.range(0, size)
                .map(i -> secureRandom.nextInt(LINE.length()))
                .mapToObj(randomIndex -> String.valueOf(LINE.charAt(randomIndex)))
                .collect(Collectors.joining());
    }
}
