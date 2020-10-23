package task1.pets;

import java.util.Comparator;
import java.util.Map;

public class PetSorter implements Comparator<Map.Entry<Integer, Pet>> {

    /**
     * Compares its two arguments for order. Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Map.Entry<Integer, Pet> o1, Map.Entry<Integer, Pet> o2) {
        String person1 = o1.getValue().getOwner().getName();
        String person2 = o2.getValue().getOwner().getName();
        if (person1.compareTo(person2) < 0) {
            return -1;
        } else if (person1.compareTo(person2) > 0) {
            return 1;
        } else {
            String petNickname1 = o1.getValue().getNickname();
            String petNickname2 = o2.getValue().getNickname();
            if (petNickname1.compareTo(petNickname2) < 0) {
                return -1;
            } else if (petNickname1.compareTo(petNickname2) > 0) {
                return 1;
            } else {
                float weight1 = o1.getValue().getWeight();
                float weight2 = o2.getValue().getWeight();
                return Float.compare(weight1, weight2);
            }
        }
    }
}
