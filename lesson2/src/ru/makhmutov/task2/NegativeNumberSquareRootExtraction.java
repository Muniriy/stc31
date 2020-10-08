package ru.makhmutov.task2;

public class NegativeNumberSquareRootExtraction extends Exception {

    /**
     * This is constructor for NegativeNumberSquareRootExtraction
     * which calls its super() method with a exception message
     *
     * @param msg the exception message
     */
    public NegativeNumberSquareRootExtraction(String msg) {
        super(msg + ": It is not allowed to extract root from negative numbers");
    }
}
