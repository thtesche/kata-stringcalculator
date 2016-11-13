package de.kata;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
 *         to change a delimiters, the beginning of the string will contain a separate line that looks like this:
 *         “//[delimiters]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiters is ‘;’ .
 *         the first line is optional. all existing scenarios should still be supported
 *         <p>
 *         5.) Calling Add with a negative number will throw an exception “negatives not allowed” -
 *         and the negative that was passed.if there are multiple negatives, show all of them in the exception message
 *         <p>
 *         <p>
 *         6.) Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
 *         <p>
 *         7.) Delimiters can be of any length with the following format:
 *         “//[delimiters]\n” for example: “//[***]\n1***2***3” should return 6
 *         8.) Allow multiple delimiters like this:
 *         “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
 *         make sure you can also handle multiple delimiters with length longer than one char
 */
public class StringCalculatorTest {

    @Test
    public void emptyInput() {
        assertThat(new StringCalculator("").add(), is(0));
    }

    @Test
    public void oneNumber() {
        assertThat(new StringCalculator("5").add(), is(5));
    }

    @Test
    public void twoNumbersCommaDelimiter() {
        assertThat(new StringCalculator("2,3").add(), is(5));
    }

    @Test
    public void unknownAmountOfNumbersCommaDelimiter() {
        assertThat(new StringCalculator("2,3,8").add(), is(13));
        assertThat(new StringCalculator("2,3,8,2,3,8").add(), is(26));

    }

    @Test
    public void newLineDelimiter() {
        assertThat(new StringCalculator("2,3\n8").add(), is(13));
    }

    @Test
    public void defineDelimiter() {
        assertThat(new StringCalculator("//;\n2;3;9").add(), is(14));
        assertThat(new StringCalculator("//;\n2,3;9").add(), is(14));
        assertThat(new StringCalculator("//;\n2,3\n9;1").add(), is(15));
    }

    @Test
    public void negativeNumbersThrowsException() {
        try {
            new StringCalculator("//;\n2;3;-9").add();
            fail();
        } catch (NumberFormatException ex) {
            assertThat(ex.getMessage(), containsString("-9"));
        }

        try {
            new StringCalculator("//;\n2;3;-9,-7").add();
            fail();
        } catch (NumberFormatException ex) {
            assertThat(ex.getMessage(), containsString("-9,-7"));
        }

    }
}