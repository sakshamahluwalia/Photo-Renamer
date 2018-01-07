package Model;

import java.util.ArrayList;

/**
 * This class will be extended in TagsManager as a part of
 * the Observer design pattern.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
class Subject {

    /**
     * ArrayList containing observers
     */
    private ArrayList<Observers> observers = new ArrayList<>();

    /**
     * Adds an observer to the observers list
     * @param o Observer
     */
    public void addObserver(Observers o) {
        observers.add(o);
    }

    /**
     * Calls the update method for all
     * the observers in the observers list
     * @param arg Object
     */
    void notifyObservers(Object arg) {
        for (Observers o : observers) {
            o.update(arg);
        }
    }
}
