package task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MathBox {

    private List<Number> numbers;

    /**
     * This is a constructor for MathBox class
     *
     * @param numbers the array of unique numbers
     */
    public MathBox(List<Number> numbers) {
        this.numbers = numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    /**
     * This is getter for the array of numbers
     *
     * @return array of numbers
     */
    public List<Number> getNumbers() {
        return numbers;
    }

    /**
     * This method allows to summarize all members of
     * the array numbers
     *
     * @return the sum of all members of numbers array
     */
    public double summation() {
        double sum = 0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * This method allows to make division by the
     * double divisor given as argument for each
     * Number array element
     *
     * @param divisor the applied divisor
     */
    public void splitter(double divisor) {
        List<Number> updatedNumbers = new ArrayList<>();
        for (Number number : numbers) {
            updatedNumbers.add(number.doubleValue() / divisor);
        }
        numbers = updatedNumbers;
    }

    /**
     * This method allows to delete Integer value from the
     * array in case of its presence
     *
     * @param value the Integer value for deletion
     */
    public boolean deleteInteger(Integer value) {
        return numbers.removeIf(value::equals);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "The array of numbers: " + getNumbers();
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     *
     * @return a hash code value for this object.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @see Object #equals()
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        MathBox mathBox = (MathBox) obj;
        return numbers.equals(mathBox.numbers);
    }

}
