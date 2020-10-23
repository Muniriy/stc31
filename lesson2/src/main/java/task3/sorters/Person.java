package task3.sorters;

public class Person {
    private final String name;
    private final String surname;
    private final int age;
    private final Sex sex;

    /**
     * This is constructor for Person class
     *
     * @param name name of a Person
     * @param surname surname of a Person
     * @param age age of a Person
     * @param sex gender of a Person
     */
    public Person(String name, String surname, int age, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    /**
     * This is getter for the Person name
     *
     * @return person name
     */
    public String getName() {
        return name;
    }

    /**
     * This is getter for the Person surname
     *
     * @return person surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This is getter for the Person age
     *
     * @return person age
     */
    public int getAge() {
        return age;
    }

    /**
     * This is getter for the Person sex
     *
     * @return person sex
     */
    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return name + " " + surname + " is a " + sex + " of " + age + " years old";
    }
}
