package task1.pets;

public class DuplicateMapValueException extends Exception {

    /**
     * This is constructor for DuplicateMapValueException
     * which calls its super() method with an exception message
     *
     * @param msg the exception message
     */
    public DuplicateMapValueException(String msg) {
        super("DuplicateMapValueException: " + msg);
    }
}
