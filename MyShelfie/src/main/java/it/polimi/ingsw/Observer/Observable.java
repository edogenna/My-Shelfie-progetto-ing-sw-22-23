package it.polimi.ingsw.Observer;

import it.polimi.ingsw.Network.messages.Message;

import java.util.ArrayList;

/**
 * Observable class that can be observed by implementing the {@link Observer} interface.
 * @author Alessandro Fornara
 */
public class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer.
     * @param observer the observer to be added.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer.
     * @param observer the observer to be added.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all the current observers through the update method and passes to them a {@link Message}.
     * @param message the message to be passed to the observers.
     */
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
