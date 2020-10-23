package task1.owners;

import task1.interfaces.ValidityChecker;

public class Person implements ValidityChecker {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 100;
    private String name;
    private Gender gender;
    private int age;

    /**
     * This is constructor for Person class
     *
     * @param name the name of a Pet owner
     * @param gender the gender of a Pet owner
     * @param age the age of a Pet owner
     */
    public Person(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * This is a getter for a Pet owner name
     *
     * @return the name of a Pet owner
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter for a Pet owner gender
     *
     * @return the gender of a Pet owner
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * This is a getter for a Pet owner age
     *
     * @return the age of a Pet owner
     */
    public int getAge() {
        return age;
    }

    /**
     * This is a setter for a Pet owner name
     *
     * @param name the name of a Pet owner
     * @throws IllegalArgumentException the exception of
     * wrong argument value
     */
    public void setName(String name) throws IllegalArgumentException {
        if (isApplicableName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name should contain only English letters" +
                    " and begin with the capital letter");
        }
    }

    /**
     * This is a setter for a Pet owner gender
     *
     * @param gender the gender of a Pet owner
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * This is a setter for a Pet owner age
     *
     * @param age the age of a Pet owner
     * @throws IllegalArgumentException the exception of
     * wrong argument value
     */
    public void setAge(int age) throws IllegalArgumentException {
        if (age >= MIN_AGE && age <= MAX_AGE) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age should be in range [" + MIN_AGE + "; " + MAX_AGE + "]");
        }
    }

    @Override
    public String toString() {
        return "Pet owner: " + name + " of " + age + " years old (" + gender.getGenderType() + ")";
    }

}
