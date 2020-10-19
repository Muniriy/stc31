package ru.makhmutov.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

public class ObjectManipulator {

    private static final Logger log = LogManager.getLogger(ObjectManipulator.class);

    /**
     * The entry point of ObjectManipulator program.
     * It allows to add and delete objects from the ArrayList
     * and display the content of the ArrayList
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {

        Collection<Object> objectCollection = new ArrayList<>();
        ObjectBox objectBox = new ObjectBox(objectCollection);

        objectBox.addObject(5);
        objectBox.addObject(6.4);
        objectBox.addObject(-7.64);

        objectBox.dump();

        objectBox.deleteObject(5);
        objectBox.deleteObject(4);

        objectBox.dump();
    }
}
