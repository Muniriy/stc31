package task3.sorters;

import java.util.Arrays;
import java.util.Comparator;

public class FirstPersonSorter implements Sortable, Comparator<Person> {

    /**
     * This method allows to sort people by next criteria:
     * gender (men first), age (elder first), name (alphabet order)
     *
     * @param people list of all people
     */
    @Override
    public void sortPeople(Person[] people) {
        Arrays.sort(people, new FirstPersonSorter());
    }

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.<p>
     * <p>
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     * <p>
     * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
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
