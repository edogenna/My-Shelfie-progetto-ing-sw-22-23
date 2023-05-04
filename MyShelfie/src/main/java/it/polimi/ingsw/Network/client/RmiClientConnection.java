package it.polimi.ingsw.Network.client;

import com.google.gson.JsonParser;
import it.polimi.ingsw.Network.server.Server;


import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.*;

import java.util.logging.Logger;

public class RmiClientConnection {
    //credo mi serva anche il server
    private Client client;
    private Server server;

    //converte i dati inviati tra client e server da e verso il formato json
    private JsonParser jsonParser;
    private ExecutorService executor;
    private Registry reg;

    private static final Logger LOGGER = Logger.getLogger("clientLogger");

    //funzioni get che possono servire
    public Client getClient() {
        return client;
    }

    public ExecutorService getExecutor() {
        return executor;
    }


    public JsonParser getJsonParser() {
        return jsonParser;
    }

    //costruttore(main del client, port a cui connettersi, indirizzo ip)
    public RmiClientConnection(Client client, int port, String address) throws RemoteException {
        this.client = client;
        //rappresenta un pool di thread con dimensione variabile
        this.executor= Executors.newCachedThreadPool();
        //getRegistry() restituisce un riferimento al
        // registro RMI sull'host specificato dall'indirizzo IP e dalla porta passati come
        // parametri in ingresso
        reg = LocateRegistry.getRegistry(address, port);
    }
/*    l'idea sarebbe quella di creare
     un thread che ciclicamente esegue un metodo 'ping' daal server RMI
     per verificare la connessione del client ,
     e in caso di disconnessione forzre la chiusura della connessione
     e segnalare la disconnessione alla view del client
*/
}
