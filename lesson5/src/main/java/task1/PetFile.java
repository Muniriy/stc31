package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.owners.Gender;
import task1.owners.Person;
import task1.pets.DuplicateMapValueException;
import task1.pets.Pet;
import task1.pets.PetSorter;

import java.util.*;

public class PetFile {

    private static final Logger log = LogManager.getLogger(PetFile.class);
    private static int lastId = 0;

    /**
     * The entry point of PetFile program.
     *
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args)  {
        Map<Integer, Pet> petMap = new HashMap<>();

        // adding 3 unique pets to the map
        addUniquePets(petMap);
        displaySortedPets(petMap);

        // adding the same 3 pets to the map and throwing a DuplicateMapValueException()
        addUniquePets(petMap);
        displaySortedPets(petMap);

        // displaying a Pet information by key
        displayPet(petMap, 1);

        // modification of a Pet weight by key
        changePetWeight(petMap, 2, 16);
    }

    /**
     * This method allows to add only unique values
     * to the Pet map
     *
     * @param petMap the map with Pets
     */
    private static void addUniquePets(Map<Integer, Pet> petMap) {
        try {
            Map<Integer, Pet> preliminaryPetMap = new HashMap<>();
            preliminaryPetMap.putAll(petMap);
            preliminaryPetMap.putAll(addPet());
            Set<String> petSet = new HashSet<>();
            for (Pet pet : preliminaryPetMap.values()) {
                petSet.add(pet.toString());
            }
            if (preliminaryPetMap.size() > petSet.size()) {
                int duplicates = preliminaryPetMap.size() - petSet.size();
                throw new DuplicateMapValueException(duplicates + " duplicates cannot be added to map");
            } else if (preliminaryPetMap.size() == petSet.size()) {
                petMap.putAll(preliminaryPetMap);
            }
        } catch (DuplicateMapValueException e) {
            log.error(e);
        }
    }

    /**
     * This method allows to display all Pets
     *
     * @param petMap the map with Pets
     */
    private static void displaySortedPets(Map<Integer, Pet> petMap) {
        if (petMap != null) {
            petMap = sortPetsByValue(petMap);
            for (Map.Entry<Integer, Pet> entry : petMap.entrySet()) {
                log.info("id = {}, {}", entry.getKey(), entry.getValue());
            }
        } else {
            log.error("Empty map");
        }
    }

    /**
     * This method allows to sort the Pet map
     * by Owner name, Pet nickname and Pet weight
     *
     * @param unsortedPetMap the unsorted map with all pets
     */
    private static Map<Integer, Pet> sortPetsByValue(Map<Integer, Pet> unsortedPetMap) {
        List<Map.Entry<Integer, Pet>> list = new LinkedList<>(unsortedPetMap.entrySet());
        list.sort(new PetSorter());
        Map<Integer, Pet> sortedPetMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Pet> entry : list) {
            sortedPetMap.put(entry.getKey(), entry.getValue());
        }
        return sortedPetMap;
    }

    /**
     * This method allows to dhow information about exact Pet
     *
     * @param petMap the map with all Pets
     * @param petId the id of Pet
     */
    private static void displayPet(Map<Integer, Pet> petMap, Integer petId) {
        if (petMap.containsKey(petId)) {
            log.info("Pet with id={}: {}", petId, petMap.get(petId));
        } else {
            log.error("Pet with id={} does not exist in the map", petId);
        }
    }

    /**
     * This method allows to change the weight
     * of a Pet by its id
     *
     * @param petMap the map with all Pets
     * @param petId the id of Pet
     * @param weight the updated weight of a Pet
     */
    private static void changePetWeight(Map<Integer, Pet> petMap, Integer petId, int weight) {
        if (petMap.containsKey(petId)) {
            petMap.get(petId).setWeight(weight);
            log.info("Weight of a Pet with id={} was changed: {}", petId, petMap.get(petId));
        } else {
            log.error("Pet with id={} does not exist in the map", petId);
        }
    }

    /**
     * This method allows to generate Pets
     */
    private static Map<Integer, Pet> addPet() {
        Map<Integer, Pet> petMap = new HashMap<>();
        try {
            Person person1 = new Person("Tim", Gender.MALE, 40);
            log.info(person1);

            Person person2 = new Person("Alice", Gender.FEMALE, 25);
            log.info(person2);

            Integer petId1 = ++lastId;
            Integer petId2 = ++lastId;
            Integer petId3 = ++lastId;

            Pet pet1 = new Pet("Spike", person1, 25.5F);
            petMap.put(petId1, pet1);

            Pet pet2 = new Pet("Pluto", person2, 15F);
            petMap.put(petId2, pet2);

            Pet pet3 = new Pet("Gary", person2, 12.4F);
            petMap.put(petId3, pet3);
        } catch (IllegalArgumentException e) {
            log.error(e);
        }
        return petMap;
    }


}
