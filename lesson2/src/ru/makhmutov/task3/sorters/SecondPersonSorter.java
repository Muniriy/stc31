package ru.makhmutov.task3.sorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SecondPersonSorter implements Sortable {
    /**
     * This method allows to sort people by next criteria:
     * gender (men first), age (elder first), name (alphabet order)
     *
     * @param people list of all people
     */
    @Override
    public void sortPeople(Person[] people) {
        Comparator<Person> comparator = Comparator.comparing(Person::getSex).reversed()
                .thenComparing(Person::getAge).reversed()
                .thenComparing(Person::getName)
                .thenComparing(Person::getSurname);
        List<Person> peopleList = new ArrayList<>(Arrays.asList(people));
        peopleList.sort(comparator);
        for (int person = 0; person < people.length; person++) {
            people[person] = peopleList.get(person);
        }
    }
}
