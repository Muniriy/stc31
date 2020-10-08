package ru.makhmutov.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class RandomNumberSquareRoots {

    private static final Logger log = LogManager.getLogger(RandomNumberSquareRoots.class);
    private static final int NUMBER_AMOUNT = 5;

    /**
     * The entry point of RandomNumberSquareRoots program.
     * It generates N integer numbers and calculates their square roots
     * if applicable. Otherwise throws exception
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        try {
            getSquareRoots(generateNumbers(NUMBER_AMOUNT));
        } catch (NegativeNumberSquareRootExtraction e) {
            log.error(e.getMessage());
        }
    }

    /**
     * This method generates array with random integer numbers
     *
     * @param numberAmount the amount of numbers
     * @return the array with integer numbers
     */
    private static int[] generateNumbers(int numberAmount) {
        Random randNumber = new Random(92);
        int[] numberArray = new int[numberAmount];
        StringBuilder stringBuilder = new StringBuilder("Generated numbers: ");
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = randNumber.nextInt(100) - 50;
            stringBuilder.append(numberArray[i]).append(" ");
        }
        stringBuilder.append("\n");
        log.info(stringBuilder);
        return numberArray;
    }

    /**
     * This method allows to get square roots from input
     * array numbers if applicable. Otherwise, throws exception
     *
     * @param numberArray the array with float numbers
     */
    private static void getSquareRoots(int[] numberArray) throws NegativeNumberSquareRootExtraction {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : numberArray) {
            if (number >= 0) {
                double squareRoot = Math.sqrt(number);
                stringBuilder.append(squareRoot).append(" ");
                if (Math.pow((int) squareRoot, 2) == number) {
                    stringBuilder.append("(the square root for ");
                    stringBuilder.append(number);
                    stringBuilder.append(") ");
                }
            } else {
                if (!stringBuilder.toString().equals("")) {
                    log.info(stringBuilder);
                }
                throw new NegativeNumberSquareRootExtraction(String.valueOf(number));
            }
        }
    }
}
