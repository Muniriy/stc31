package task1.sorters;

import task1.DuplicatedPersonException;

import java.util.HashSet;
import java.util.Set;

public interface Sortable {

    /**
     * This method allows to sort people by next criteria:
     * gender (men first), age (elder first), name (alphabet order)
     *
     * @param people list of all people
     */
    default void sortPeople(Person[] people) {
        System.out.println("Undefined method. Do not use it");
    }

    /**
     * This method allows to sort people by next criteria:
     * gender (men first), age (elder first), name (alphabet order)
     *
     * @param  people list of all people
     * @return array with sorted people
     */
    default Person[] sortPeopleArray(Person[] people) {
        System.out.println("Undefined method. Do not use it");
        return people;
    }

    default void findDuplicatedPeople(Person[] people) throws DuplicatedPersonException {
        Set<String> peopleSet = new HashSet<>();
        Set<String> duplicatedPeopleSet = new HashSet<>();
        for (Person person : people) {
            String personString = person.getName() + " " + person.getSurname() + " " + person.getAge();
            if (!peopleSet.contains(personString)) {
                peopleSet.add(personString);
            } else {
                duplicatedPeopleSet.add(personString);
            }
        }
        if (peopleSet.size() != people.length) {
            StringBuilder errorMsg = new StringBuilder("The first method has detected " +
                    duplicatedPeopleSet.size() + " duplicated people:\n");
            for (String duplicate : duplicatedPeopleSet) {
                errorMsg.append(duplicate);
                errorMsg.append("\n");
            }
            throw new DuplicatedPersonException(errorMsg.toString());
        }
    }

}
