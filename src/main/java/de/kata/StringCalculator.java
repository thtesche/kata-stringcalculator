package de.kata;


import lombok.Getter;

/**
 * @author thtesche
 */
class StringCalculator {

    private String input;

    StringCalculator(final String input) {
        this.input = input;
    }

    int add() throws NumberFormatException {
        int sum;
        sum = 0;

        PreSplitter preSplitter = new PreSplitter(input);
        StringBuilder negativeNumberConcatenation = new StringBuilder();

        if (input.length() > 0) {
            String[] numberStrings = preSplitter.getNumbers()
                    .split("[" + preSplitter.getDelimiters() + "]");
            for (String numberString : numberStrings) {
                if (Integer.valueOf(numberString) < 0) {
                    negativeNumberConcatenation.append(numberString);
                    negativeNumberConcatenation.append(",");
                }
                sum += Integer.valueOf(numberString);
            }
            checkForNegativeNumbersAndThrowException(negativeNumberConcatenation);
        }

        return sum;

    }

    private void checkForNegativeNumbersAndThrowException(final StringBuilder stringBuilder)
            throws NumberFormatException {

        if (stringBuilder.length() > 0) {
            String message = stringBuilder.toString().substring(0,
                    stringBuilder.toString().lastIndexOf(','));

            throw new NumberFormatException(message);
        }
    }

    private class PreSplitter {
        @Getter
        private String delimiters = ",\n";
        @Getter
        private String numbers;

        PreSplitter(final String input) {
            if (input.startsWith("//")) {
                delimiters += input.substring(2, 3);
                numbers = input.substring(4);
            } else {
                numbers = input;
            }
        }
    }
}
