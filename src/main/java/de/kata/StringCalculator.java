package de.kata;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author thtesche
 */
class StringCalculator {
    private final String input;

    StringCalculator(String input) {
        this.input = input;
    }

    int calc() throws NegativesNotAllowedException {

        if (input.length() == 0) return 0;

        return calculateSum();
    }

    private int calculateSum() throws NegativesNotAllowedException {
        StringBuilder exceptionMessage = new StringBuilder();
        int sum = 0;

        for (Integer number : getSplittedNumbers()) {
            if (number < 0) {
                exceptionMessage.append(number).append(",");

            } else if (number <= 1000) {
                sum += number;
            }
        }

        if (exceptionMessage.length() > 0) {
            String message = exceptionMessage.toString().substring(0, exceptionMessage.length());
            throw new NegativesNotAllowedException(message);
        }
        return sum;
    }

    private List<Integer> getSplittedNumbers() {
        return Arrays.stream(input.split("[,\n]"))
                .map(Integer::valueOf)
                .collect(toList());
    }
}
