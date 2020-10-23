package task1.pets;

import task1.interfaces.ValidityChecker;
import task1.owners.Person;

public class Pet implements ValidityChecker {

    private static final float MIN_WEIGHT = 0.5F;
    private static final float MAX_WEIGHT = 100F;
    private String nickname;
    private Person owner;
    private float weight;

    /**
     * This is constructor for PetData class
     *
     * @param nickname the nickname of a Pet
     * @param owner the owner of a Pet
     * @param weight the weight of a Pet
     */
    public Pet(String nickname, Person owner, float weight) {
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * This is a getter for a Pet nickname
     *
     * @return the nickname of a Pet
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This is a getter for a Pet owner
     *
     * @return the owner of a Pet
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * This is a getter for a Pet weight
     *
     * @return the weight of a Pet
     */
    public float getWeight() {
        return weight;
    }

    /**
     * This is a setter for a Pet nickname
     *
     * @param nickname the Pet nickname
     * @throws IllegalArgumentException the exception of
     * wrong argument value
     */
    public void setNickname(String nickname) throws IllegalArgumentException {
        if (isApplicableName(nickname)) {
            this.nickname = nickname;
        } else {
            throw new IllegalArgumentException("Nickname should contain only English letters" +
                    " and begin with the capital letter");
        }
    }

    /**
     * This is a setter for a Pet owner
     *
     * @param owner the Pet owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * This is a setter for a Pet weight
     *
     * @param weight the weight of a Pet
     * @throws IllegalArgumentException the exception of
     * wrong argument value
     */
    public void setWeight(float weight) throws IllegalArgumentException {
        if (weight > MIN_WEIGHT && weight < MAX_WEIGHT) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight should be in range [" + MIN_WEIGHT + "; " + MAX_WEIGHT + "]");
        }
    }

    @Override
    public String toString() {
        return "nickname='" + nickname + '\'' +
                ", owner=" + owner +
                ", weight=" + weight;
    }
}
