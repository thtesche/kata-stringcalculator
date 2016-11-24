package de.kata;

import java.util.Arrays;

/**
 * @author thtesche
 */
public class StringCalculator {

    private static final String DEFAULT_DELIMITER_REGEX = "[,\n]";
    private String[] numbers = null;

    public StringCalculator(String input) {

        String regex = extractDelimiterRegex(input);

        String numberString = extractNumberString(input);

        numbers = extractNumbers(numberString, regex);

    }

    int add() {
        return Arrays.stream(numbers).mapToInt(Integer::valueOf).sum();
    }

    private String extractNumberString(String input) {
        String numberString = input;
        if (input.startsWith("//")) {
            numberString = input.substring(4);
        }
        return numberString;
    }

    private String[] extractNumbers(final String numberString, final String regex) {
        if (numberString.length() > 0) {
            return numberString.split(regex);
        } else {
            return new String[]{"0"};
        }
    }

    private String extractDelimiterRegex(final String input) {
        if (input.startsWith("//")) {
            return input.substring(2, 3);
        } else {
            return DEFAULT_DELIMITER_REGEX;
        }
    }
}
