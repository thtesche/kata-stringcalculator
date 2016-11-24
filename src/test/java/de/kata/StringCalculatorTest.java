package de.kata;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author thtesche
 *         <p>
 *         The following requirements/rules are from http://osherove.com/tdd-kata-1/
 *         <p>
 *         REQUIREMENTS
 *         ************
 *         <p>
 *         Create a simple String calculator with a method int Add(string numbers)
 *         Start with the simplest test case of an empty string and move to 1 and two numbers
 *         Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
 *         Remember to refactor after each passing test
 *         <p>
 *         1.) The method can take 0, 1 or 2 numbers, and will return their sum
 *         (for an empty string it will return 0) for example “” or “1” or “1,2”
 *         <p>
 *         2.) Allow the Add method to handle an unknown amount of numbers
 *         <p>
 *         3.) Allow the Add method to handle new lines between numbers (instead of commas).
 *         the following input is ok:  “1\n2,3”  (will equal 6)
 *         the following input is NOT ok:  “1,\n” (not need to prove it - just clarifying)
 *         <p>
 *         4.) Support different delimiters
 *         to change a delimiter, the beginning of the string will contain a separate line that looks like this:
 *         “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
 *         the first line is optional. all existing scenarios should still be supported
 *         <p>
 *         5.) Calling Add with a negative number will throw an exception “negatives not allowed” -
 *         and the negative that was passed.if there are multiple negatives, show all of them in the exception message
 *         <p>
 *         <p>
 *         6.) Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
 *         <p>
 *         7.) Delimiters can be of any length with the following format:
 *         “//[delimiter]\n” for example: “//[***]\n1***2***3” should return 6
 *         8.) Allow multiple delimiters like this:
 *         “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
 *         make sure you can also handle multiple delimiters with length longer than one char
 */
public class StringCalculatorTest {

    @Test
    public void emptyInput() throws NegativeNumberException {
        assertThat(new StringCalculator("").add(), is(0));
    }

    @Test
    public void oneNumber() throws NegativeNumberException {
        assertThat(new StringCalculator("1").add(), is(1));
        assertThat(new StringCalculator("33").add(), is(33));
    }

    @Test
    public void twoNumbers() throws NegativeNumberException {
        assertThat(new StringCalculator("1,13").add(), is(14));
    }

    @Test
    public void arbitraryCountOfNumbers() throws NegativeNumberException {
        assertThat(new StringCalculator("1,13,26,100").add(), is(140));
    }

    @Test
    public void newLineAsDelimiter() throws NegativeNumberException {
        assertThat(new StringCalculator("1,13\n26,100").add(), is(140));
    }

    @Test
    public void delimiterDefinitionAtFirstLine() throws NegativeNumberException {
        assertThat(new StringCalculator("//;\n1;13;26;100").add(), is(140));
    }

    @Test
    public void noNegativeNumbers() {
        try {
            new StringCalculator("//;\n1;-13;26;-100").add();
            fail();
        } catch (NegativeNumberException exception) {
            assertThat(exception.getMessage(), containsString("-100"));
            assertThat(exception.getMessage(), containsString("-13"));
        }
    }

    @Test
    public void numbersGreater1000AreNotAllowed() throws NegativeNumberException {
        assertThat(new StringCalculator("//;\n1;13;26;100;1001").add(), is(140));
        assertThat(new StringCalculator("1001").add(), is(0));
    }

    @Test
    public void moreThanOneDelimiterChar() throws NegativeNumberException {
        assertThat(new StringCalculator("//[;;;]\n1;;;13;;;26;;;100;;;1001").add(), is(140));
    }

}