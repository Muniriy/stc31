package task1.sorters;

import java.util.Arrays;
import java.util.Comparator;

public class ThirdPersonSorter implements Sortable, Comparator<Person> {

    /**
     * This method allows to sort people by next criteria:
     * gender (men first), age (elder first), name (alphabet order)
     *
     * @param  people list of all people
     * @return array with sorted people
     */
    @Override
    public Person[] sortPeopleArray(Person[] people) {
        return Arrays.stream(people)
                .sorted(new ThirdPersonSorter())
                .toArray(Person[]::new);
    }

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second
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
    public int compare(Person o1, Person o2) {
        Sex sex1 = o1.getSex();
        Sex sex2 = o2.getSex();
        if (sex1 != sex2) {
            if (sex1 == Sex.MAN) {
                return -1;
            } else {
                return 1;
            }
        } else {
            int age1 = o1.getAge();
            int age2 = o2.getAge();
            if (age1 > age2) {
                return -1;
            } else if (age1 < age2) {
                return 1;
            } else {
                String name1 = o1.getName();
                String name2 = o2.getName();
                if (name1.compareTo(name2) < 0) {
                    return -1;
                } else if (name1.compareTo(name2) > 0) {
                    return 1;
                } else {
                    String surname1 = o1.getSurname();
                    String surname2 = o2.getSurname();
                    return surname1.compareTo(surname2);
                }

            }
        }
    }
}
