package ru.makhmutov.task3;

public class DuplicatedPersonException extends Exception {

    /**
     * This is constructor for NegativeNumberSquareRootExtraction
     * which calls its super() method with a exception message
     *
     * @param msg the exception message
     */
    public DuplicatedPersonException(String msg) {
        super("DuplicatedPersonException: " + msg);
    }
}
