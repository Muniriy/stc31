package ru.makhmutov.task3;

import java.util.Collection;

public class ObjectBox {

    private final Collection<Object> objectCollection;

    /**
     * This is a constructor for ObjectBox class
     *
     * @param objectCollection the object collection
     */
    public ObjectBox(Collection<Object> objectCollection) {
        this.objectCollection = objectCollection;
    }

    /**
     * This method allows to add object to the collection
     *
     * @param obj the object for addition
     */
    public void addObject(Object obj) {
        objectCollection.add(obj);
    }

    /**
     * This method allows to delete object from the collection
     * if it is present
     *
     * @param obj the object for addition
     */
    public void deleteObject(Object obj) {
        if (objectCollection.contains(obj)) {
            objectCollection.remove(obj);
        } else {
            System.out.println(obj + " does not exist inside collection");
        }
    }

    /**
     * This method allows to display the object collection
     * in console
     */
    public void dump() {
        System.out.println("All objects: ");
        for (Object obj : objectCollection) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
