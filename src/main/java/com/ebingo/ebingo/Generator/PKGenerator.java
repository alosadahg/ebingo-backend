package com.ebingo.ebingo.Generator;

import java.security.SecureRandom;

public class PKGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom random = new SecureRandom();

    public static String generate(String type) {
        int length = (type.equalsIgnoreCase("game_code")) ? 8 : 16;
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
}
