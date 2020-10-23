package task3;

public class DuplicatedPersonException extends Exception {

    /**
     * This is constructor for DuplicatedPersonException
     * which calls its super() method with an exception message
     *
     * @param msg the exception message
     */
    public DuplicatedPersonException(String msg) {
        super("DuplicatedPersonException: " + msg);
    }
}
