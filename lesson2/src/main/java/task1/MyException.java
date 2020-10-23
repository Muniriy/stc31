package task1;

public class MyException extends Exception {

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "Exception: It is not allowed to print this phrase!";
    }
}
