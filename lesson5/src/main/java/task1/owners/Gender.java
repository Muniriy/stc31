package task1.owners;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non-binary");

    private final String genderType;

    /**
     * This is constructor for the Person gender
     *
     * @param gender the Person gender
     */
    Gender(String gender) {
        this.genderType = gender;
    }

    /**
     * This is the getter for the Person gender
     *
     * @return the Person gender
     */
    public String getGenderType() {
        return genderType;
    }
}
