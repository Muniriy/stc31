package task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task3.sorters.*;

import java.util.Arrays;
import java.util.Random;

public class PersonSorter {

    private static final Logger log = LogManager.getLogger(PersonSorter.class);
    private static final int PEOPLE_AMOUNT = 100;

    /**
     * The entry point of RandomNumberSquareRoots program.
     * It generates N integer numbers and calculates their square roots
     * if applicable. Otherwise throws exception
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        sortPeople(generatePeople(PEOPLE_AMOUNT));
    }

    /**
     * This method allows to sort people sequentially by 2 means,
     * both of which use Comparator interface. The first one
     * redefines compareTo() method. The second one uses
     * ArrayList and lambda-expressions
     *
     * @param people the array of all people for sorting
     */
    private static void sortPeople(Person[] people) {
        Sortable firstPersonSorter = new FirstPersonSorter();
        Sortable secondPersonSorter = new SecondPersonSorter();
        Person[] people2 = Arrays.copyOf(people, people.length);
        sorter(firstPersonSorter, people);
        sorter(secondPersonSorter, people2);
    }

    /**
     * This method allows to sort people for any
     * of two methods
     *
     * @param personSorter the object of the sorter class
     * @param people the array of all people for sorting
     */
    private static void sorter(Sortable personSorter, Person[] people) {
        try {
            long startTime = System.nanoTime();
            personSorter.sortPeople(people);
            long endTime = System.nanoTime();
            log.info("The {} has sorted all people for {} ns",
                    personSorter.getClass().getSimpleName(), (endTime - startTime));
            personSorter.findDuplicatedPeople(people);
            displayPeople(people);
        } catch (DuplicatedPersonException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * This method allows to display all people
     *
     * @param people the array of all people
     */
    private static void displayPeople(Person[] people) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < people.length; i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(people[i]);
            stringBuilder.append("\n");
        }
        stringBuilder.append("The people are displayed");
        stringBuilder.append("\n");
        log.info(stringBuilder);
    }

    /**
     * This method allows to generate people
     *
     * @param peopleAmount the number of people
     */
    private static Person[] generatePeople(int peopleAmount) {
        Person[] people = new Person[peopleAmount];
        Random rand = new Random();
        String[] possibleMenNames =
                {"Alex", "Max", "Joseph", "Michael", "Antony", "Andrew", "Bruce", "Philipp", "Brian", "Tom",
                "Jerry", "Rick", "John", "Nick", "Justin", "Corey", "Paul", "Denis", "Tim", "Mark",
                "Ted", "Rupert", "Harry", "Jared", "Collin", "Samuel", "Ernesto", "Billy", "William", "Jimmy"};
        String[] possibleWomenNames =
                {"Alice", "Susanna", "May", "Maggie", "Anna", "Jennifer", "Patty", "Nicole", "Kate", "Brianna",
                        "Caroline", "Cloe", "Marry", "Audrey", "Liana", "Diana", "Jade", "Monica", "Alice", "Amy",
                "Lucy", "Jane", "Natalie", "Emily", "Monday", "Veronica", "Casandra", "Linda", "Tanya", "Amanda"};
        String[] possibleSurnames =
                {"Brown", "Johnson", "Black", "Snow", "Jackson", "Willis", "Anniston", "Williams", "Taylor", "Cage",
                "Ford", "Butler", "Stone", "Blair", "Bennington", "Trump", "Chase", "Bush", "Lee", "Hanner",
                "Dickson", "Dent", "Bullock", "Cameron", "Costner", "Farrell", "Radcliff", "Watson", "Perry", "Smith"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < people.length; i++) {
            int age = rand.nextInt(100) + 1;
            int sexNumber = rand.nextInt(Sex.values().length);
            String surname = possibleSurnames[rand.nextInt(possibleSurnames.length)];
            String name = null;
            Sex sex = null;
            switch (sexNumber) {
                case 0:
                    sex = Sex.MAN;
                    name = possibleMenNames[rand.nextInt(possibleMenNames.length)];
                    break;
                case 1:
                    sex = Sex.WOMAN;
                    name = possibleWomenNames[rand.nextInt(possibleWomenNames.length)];
                    break;
                default:
                    log.error("Error: Only 2 genders are applicable");
            }
            people[i] = new Person(name, surname, age, sex);
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(people[i]);
            stringBuilder.append("\n");
        }
        stringBuilder.append("People are generated\n");
        log.info(stringBuilder);
        return people;
    }
}
