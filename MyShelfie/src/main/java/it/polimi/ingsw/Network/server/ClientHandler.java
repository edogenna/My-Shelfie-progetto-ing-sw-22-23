package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.ClientInformation;
import it.polimi.ingsw.Network.messages.UsernameAnswer;
import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.messages.NumberOfPlayersAnswer;

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
            int i = 1;
            while (true) {
                if(i == 1){
                    Server.lock.lock();
                }
                String line = clientInformation.getIn().nextLine();

                if (line.equals("quit")) {
                    break;
                } else {
                    Message m = c.convertFromJSON(line);
                    switch (m.getType()){
                        case "NumberOfPlayers" -> {
                            server.setNumberOfPlayers(((NumberOfPlayersAnswer) m).getNum());
                            Server.lock.unlock();
                            i--;
                        }
                        case "UsernameAnswer" -> {
                            server.setStr(((UsernameAnswer) m).getS());
                        }
                    }
                }
            }
            //Chiudo gli stream e il socket
            clientInformation.getIn().close();
            clientInformation.getOut().close();
            clientInformation.getSocket().close();
            System.out.println("User disconnected");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Message message) {
        sendMessage(message, clientInformation.getOut());
    }

    public void sendMessage(Message m, PrintWriter out){
        String jsonString = c.convertToJSON(m);
        out.println(jsonString);
    }

    public ClientInformation getClientInformation() {
        return clientInformation;
    }
}

