package de.kata;

/**
 * @author thtesche
 */
class StringCalculator {
    private final String input;

    StringCalculator(String input) {
        this.input = input;
    }

    int calc() throws NegativesNotAllowedException {
        int sum = 0;
        StringBuilder exceptionMessage = new StringBuilder();

        if (input.length() == 0) {
            return sum;
        }

        String[] splittedNumbers = input.split("[,\n]");
        for (String numberString : splittedNumbers) {
            int number = Integer.valueOf(numberString);
            if (number < 0) {
                exceptionMessage.append(numberString);
                exceptionMessage.append(",");

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
}
