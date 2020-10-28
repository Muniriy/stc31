package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FactorialCalculator {

    private static final Logger log = LogManager.getLogger(FactorialCalculator.class);
    private static final int ARRAY_SIZE = 10;
    private static final int MIN_ARRAY_NUMBER = 0;
    private static final int MAX_ARRAY_NUMBER = 50;

    /**
     * This is the entry point of the FactorialCalculator program.
     * It allows to calculate factorials for the array of
     * integer numbers
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        Integer[] numbers = generateArray(ARRAY_SIZE, MIN_ARRAY_NUMBER, MAX_ARRAY_NUMBER);
        Arrays.sort(numbers);
        displayArray(numbers, "number");
        ArrayList<BigInteger> factorialNumbers = (ArrayList<BigInteger>) calculateFactorial(numbers);
        displayArray(factorialNumbers.toArray(new Number[0]), "factorial number");
    }

    /**
     * This method allows to calculate factorials for
     * numbers in the array
     *
     * @param numbers the array with numbers
     */
    private static List<BigInteger> calculateFactorial(Integer[] numbers) {
        return Arrays.stream(numbers).filter(i -> i >= MIN_ARRAY_NUMBER)
                .collect(Collectors.toSet())
                .stream().parallel()
                .map(FactorialCalculator::getFactorial)
                .collect(Collectors.toList())
                .stream().sorted()
                .collect(Collectors.toList());
    }

    /**
     * This method allows to calculate factorial for
     * integer number
     *
     * @param number the number for which factorial hs to
     *               be calculated
     * @return       the calculated factorial
     */
    private static BigInteger getFactorial(Integer number) {
        BigInteger res = BigInteger.ONE;
        if (number != 0) {
            for (int i = number; i > 0; i--) {
                res = res.multiply(BigInteger.valueOf(i));
            }
        }
        return res;
    }

    /**
     * This method allows to display the number array
     *
     * @param numbers the array with numbers
     * @param title    the title of content contained in
     *                the array
     */
    private static void displayArray(Number[] numbers, String title) {
        StringBuilder numberString = new StringBuilder("The " + title + " array: ");
        for (Number number : numbers) {
            numberString.append(number);
            numberString.append(" ");
        }
        log.info(numberString);
    }

    /**
     * This method allows to generate the array of integer
     * numbers
     *
     * @param size the size of the array
     * @return the generated number array
     */
    private static Integer[] generateArray(int size, int minNumber, int maxNumber) {
        Integer[] numberArray = new Integer[size];
        Random random = new Random();
        for (int curNumber = 0; curNumber < size; curNumber++) {
            numberArray[curNumber] = random.nextInt(maxNumber) + minNumber;
        }
        return numberArray;
    }
}
