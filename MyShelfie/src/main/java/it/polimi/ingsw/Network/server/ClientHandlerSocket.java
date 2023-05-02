package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.MoveAnswer;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidMoveError;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.Observer.Observer;

import java.io.IOException;

/**
 * This class is used to respond to requests from a specific socket client
 * @author Alessandro Fornara
 */
public class ClientHandlerSocket implements Runnable, Observer, Connection {
    private Server server;
    private Observable observable;
    private ClientInformation clientInformation;
    private final Converter c = new Converter();
    private String line;
    private Message m;

    public ClientHandlerSocket(Server server, ClientInformation client, Observable observable) {
        this.server = server;
        this.clientInformation = client;
        this.observable = observable;
        this.observable.addObserver(this);
        this.line = null;
        this.m = null;
    }

    @Override
    public void run() {
        try {
            chooseUsernamePhase();
            while (true) {
                line = clientInformation.getIn().nextLine();

                if (line.equals("quit")) {
                    break;
                } else {
                    m = c.convertFromJSON(line);
                    switch (m.getType()){
                        case "NumberOfPlayers" -> handleNumberOfPlayerAnswer(m);
                        case "Move" -> handleMoveAnswer(m);
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
        sendMessage(message);
    }

    /**
     * This method sends a message to a client
     * @param m {@link Message}
     */
    @Override
    public void sendMessage(Message m){
        String jsonString = c.convertToJSON(m);
        clientInformation.getOut().println(jsonString);
    }

    /**
     * This method handles a {@link NumberOfPlayersAnswer} from the client
     * @author Alessandro Fornara
     * @param m {@link Message}
     */
    private void handleNumberOfPlayerAnswer(Message m){
        server.setNumberOfPlayers(((NumberOfPlayersAnswer) m).getNum());
        Server.Lock1.release();
    }

    /**
     * This method handles a {@link MoveAnswer} from the client
     * @author Alessandro Fornara
     * @param m {@link Message}
     */
    private void handleMoveAnswer(Message m){
        if (server.controller.dummyInput(((MoveAnswer) m).getS())){
            sendMessage(new NotValidMoveError());
        }
        else {
            String[] tiles = ((MoveAnswer) m).getS().split(",");
            int i = tiles.length;
            boolean done = false;
            //i = number of tiles * 2 + 1;
            switch (i) {
                case 3:
                    //we have taken 1 tile;
                    done = server.controller.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]));
                    break;
                case 5:
                    //we have taken 2 tiles;
                    done = server.controller.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0)-'a', Integer.parseInt(tiles[3]), Integer.parseInt(tiles[4]));
                    break;
                case 7:
                    //we have taken 3 tiles;
                    done = server.controller.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0)-'a', Integer.parseInt(tiles[3]), tiles[4].charAt(0)-'a', Integer.parseInt(tiles[5]), Integer.parseInt(tiles[6]));
                    break;
            }

            if(!done){
                sendMessage(new NotValidMoveError());
            }else{
                server.win = server.controller.finishTurn();
                Server.Lock1.release();
            }
        }
    }

    /**
     * This method is used during the lobby phase and allows the user to choose a username
     * @author Alessandro Fornara
     */
    private void chooseUsernamePhase(){
        sendMessage(new ChooseUsernameMessage());
        line = clientInformation.getIn().nextLine();

        Message m = c.convertFromJSON(line);

        while(!server.isUsernameTaken(((UsernameAnswer) m).getS())){
            sendMessage(new NotValidUsernameError());
            line = clientInformation.getIn().nextLine();
            m = c.convertFromJSON(line);
        }

        Server.Lock2.release();
    }
}

