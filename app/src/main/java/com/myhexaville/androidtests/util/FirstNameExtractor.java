package com.myhexaville.androidtests.util;

public class FirstNameExtractor {

    public static String extractFirstName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return "";
        }

        String[] split = fullName.split(" ");
        for (String word : split) {
            if (!word.isEmpty()) {
                return word;
            }
        }

        return null;
    }
}
