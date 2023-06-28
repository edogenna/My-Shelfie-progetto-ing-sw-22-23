package it.polimi.ingsw.Network.client;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class is the interface of a rmi client
 * @author Donato Fiore
 */
public interface RmiClientInterface extends Remote {

    /**
     * Sends a message to the server and waits for an answer
     * @param message The message to send
     * @return The answer
     * @throws IOException
     * @throws InterruptedException
     */
    String sendMessageAndGetAnswer(String message) throws IOException, InterruptedException;

    /**
     * Tests the connection with the server
     * @return True if the connection is still alive, false otherwise
     * @throws RemoteException
     */
    boolean testAliveness() throws RemoteException;

    /**
     * Closes the client
     * @throws RemoteException
     */
    void stopClient() throws RemoteException;

    /**
     * Sets the id of the client
     * @param number The id of the client
     * @throws RemoteException
     */
    void setId(int number) throws RemoteException;
}
