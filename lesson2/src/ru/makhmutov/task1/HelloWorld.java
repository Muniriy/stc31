package ru.makhmutov.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class HelloWorld {

    private static final Logger log = LogManager.getLogger(HelloWorld.class);

    private static final String MESSAGE = "Hello, World!";
    private static final int MINIMAL_STRING_LENGTH = 2;

    /**
     * The entry point of HelloWorld program.
     * It allows to choose one of input values for
     * different exceptions calling:
     *      1 - ArrayIndexOutOfBoundsException
     *      2 - NullPointerException
     *      3 - MyException
     *      4 - Program exit
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        int exceptionCase = 0;
        while(exceptionCase != 4) {
            exceptionCase = scanInt(1, 4);
            switch (exceptionCase) {
                case 1:
                    arrayOutOfBoundsExceptionCaller(5);
                    break;
                case 2:
                    nullPointerExceptionCaller();
                    break;
                case 3:
                    customExceptionCaller();
                    break;
                case 4:
                    log.info("See you later!");
                    break;
                default:
                    log.error("Error: non-existing case");
            }
        }
    }

    /**
     * This method fills each element of String array with the same message
     * and prints the content. Intentionally the method tries to fill the
     * element of the array out of bounds to get ArrayIndexOutOfBoundsException
     */
    private static void arrayOutOfBoundsExceptionCaller(int repetitions) {
        String[] array = new String[repetitions];
        for (int i = 0; i <= array.length; i++) {
            array[i] = MESSAGE;
            log.info(array[i]);
        }
    }

    /**
     * This method fills the 1st (not 0th) element of String array by message
     * and prints all elements of the array in original and lower case,
     * because of which NullPointerException is achieved for 0th null element
     *
     */
    private static void nullPointerExceptionCaller() {
        String[] array = new String[2];
        array[1] = MESSAGE;
        for (String msg : array) {
            log.info(msg);
            String lowerCaseMsg = msg.toLowerCase();
            log.error(lowerCaseMsg);
        }
    }

    /**
     * This method reads the input message and prints it if
     * it is not forbidden or throws custom MyException otherwise
     *
     */
    private static void customExceptionCaller() {
        String inputMessage = scanString();
        try {
            printMessage(inputMessage);
        } catch (MyException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * This methods prints the input message if it is not forbidden
     * or throws the exception otherwise
     *
     * @throws MyException in case of forbidden message
     */
    public static void printMessage(String inputMessage) throws MyException {
        if (inputMessage.equals(MESSAGE)) {
            throw new MyException();
        } else {
            log.info(inputMessage);
        }
    }

    /**
     * This method allows to read valid input String values.
     *
     * @return The obtained value received via scanning
     */
    private static String scanString() {
        Scanner scanner = new Scanner(System.in);
        String value;
        boolean validityFlag = false;
        log.info("Type the message: ");
        do {
            value = scanner.nextLine();
            if (value.length() >= MINIMAL_STRING_LENGTH) {
                validityFlag = true;
            } else {
                log.warn("\nUse strings containing not less than {} symbols," +
                        "\ntry one more time: ", MINIMAL_STRING_LENGTH);
            }
        } while (!validityFlag);
        return value;
    }

    /**
     * This method allows to read valid integer values.
     *
     * @param lowerBoundary The lower boundary of the value
     * @param upperBoundary The upper boundary of the value
     * @return The obtained integer value received via scanning
     */
    private static int scanInt(int lowerBoundary, int upperBoundary) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        int value = 0; // the initial value should not be positive to be a barrier for the program
        log.info("Here are exception options: \n" +
                "\t1 - ArrayIndexOutOfBoundsException\n" +
                "\t2 - NullPointerException\n" +
                "\t3 - MyException\n" +
                "\t4 - Program exit\n" +
                "Choose the exception you want to call by typing one of the above-mentioned numbers: ");
        do {
            try {
                value = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                log.warn("Please enter only integer values, try one more time: ");
                scanner.next();
                continue;
            }
            if (value < lowerBoundary || value > upperBoundary) {
                String warnMsg = String.format("Please enter the value in range [%d; %d], try one more time: ",
                        lowerBoundary, upperBoundary);
                log.warn(warnMsg);
            }
        } while (value < lowerBoundary || value > upperBoundary);
        return value;
    }
}
