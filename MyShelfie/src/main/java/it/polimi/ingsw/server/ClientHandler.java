package it.polimi.ingsw.server;

import it.polimi.ingsw.Client.ClientInformation;
import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.controller.messages.Converter;
import it.polimi.ingsw.controller.messages.Message;
import it.polimi.ingsw.controller.messages.NumberOfPlayersAnswer;

import java.io.IOException;
import java.io.PrintWriter;

public class ClientHandler implements Runnable, Observer {
    private Server server;
    private Observable observable;
    private ClientInformation clientInformation;
    private final Converter c = new Converter();

    public ClientHandler(Server server, ClientInformation client, Observable observable) {
        this.server = server;
        this.clientInformation = client;
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void run() {
        try {

            //Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (true) {
                server.simophore.acquire();
                String line = clientInformation.getIn().nextLine();

                System.out.println(line);
                if (line.equals("quit")) {
                    break;
                } else {
                    Message m = c.convertFromJSON(line);
                    server.setNumberOfPlayers(((NumberOfPlayersAnswer) m).getNum());
                    server.simophore.release();
                }
            }
            //Chiudo gli stream e il socket
            clientInformation.getIn().close();
            clientInformation.getOut().close();
            clientInformation.getSocket().close();
            System.out.println("User disconnected");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Message message) {
        sendMessage(message, clientInformation.getOut());
    }

    private void sendMessage(Message m, PrintWriter out){
        String jsonString = c.convertToJSON(m);
        out.println(jsonString);
    }

    public ClientInformation getClientInformation() {
        return clientInformation;
    }
}

