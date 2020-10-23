package task1.interfaces;

public interface ValidityChecker {

    /**
     * This methods returns true if the string consists
     * of only English alphabet letters and begins with
     * the capital letter
     *
     * @param name the input String
     * @return true or false
     */
    default boolean isApplicableName(String name) {
        return name.matches("[A-Z][a-z]+");
    }
}
