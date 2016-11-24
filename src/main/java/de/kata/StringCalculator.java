package de.kata;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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

    int add() throws NegativeNumberException {
        checkForNegativeNumbers();
        return Arrays.stream(numbers)
                .mapToInt(Integer::valueOf)
                .filter(value -> value <= 1000)
                .sum();
    }

    private void checkForNegativeNumbers() throws NegativeNumberException {
        String negativeNumbersMessage = Arrays.stream(numbers)
                .filter(value -> Integer.valueOf(value) < 0)
                .collect(joining(","));

        if (negativeNumbersMessage.length() > 0) {
            throw new NegativeNumberException(negativeNumbersMessage);
        }
    }

    private String extractNumberString(String input) {

        if (input.startsWith("//")) {
            return input.substring(input.indexOf("\n") + 1);
        } else {
            return input;
        }
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
            String rawRegex = input.substring(2, input.indexOf("\n"));
            if (rawRegex.length() == 1) {
                return rawRegex;
            } else {
                return findAllRegexes(rawRegex);
            }

        } else {
            return DEFAULT_DELIMITER_REGEX;
        }
    }

    private String findAllRegexes(String definitionString) {
        String[] definitions = definitionString.split("]");
        List<String> cleanedDefinitions = Arrays.stream(definitions)
                .map(s -> escapeDelimiters(s.replace("[", "")))
                .collect(toList());

        return cleanedDefinitions.stream().collect(joining("|"));
    }

    private String escapeDelimiters(final String unescapedDelimiter) {
        return unescapedDelimiter.chars()
                .mapToObj(i -> String.valueOf((char) i))
                .collect(joining("", "\\", ""));
    }
}
