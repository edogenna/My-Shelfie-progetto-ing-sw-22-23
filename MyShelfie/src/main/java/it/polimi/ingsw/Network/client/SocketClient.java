package it.polimi.ingsw.Network.client;


import it.polimi.ingsw.Network.messages.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class SocketClient {
    private Converter c = new Converter();
    public void startSocketClient() throws IOException {

        String hostName = "127.0.0.1";
        int portNumber = 1234;

        /*if (args.length != 2) {
            System.err.println(
                "Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        */
        try (
                Socket Socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            Converter c = new Converter();
            //CliView cliView = new CliView();

            while (true) {
                String message = in.readLine();
                Message m = c.convertFromJSON(message);

                switch (m.getType()) {
                    case "FirstPlayer" -> {
                        System.out.println(((FirstPlayerMessage) m).getS());
                        userInput = stdIn.readLine();
                        m = new NumberOfPlayersAnswer(Integer.parseInt(userInput));
                        out.println(c.convertToJSON(m));
                    }
                    case "Lobby" -> System.out.println(((LobbyMessage) m).getS());
                    case "StartingGame" -> {
                        System.out.println(((StartingGameMessage) m).getS());
                    }
                    case "ChooseUsername" -> {
                        System.out.println(((ChooseUsernameMessage) m).getS());
                        userInput = stdIn.readLine();
                        out.println(c.convertToJSON(new UsernameAnswer(userInput)));
                    }
                    /*case "GameInformation" -> {
                        GameInformation gameInformation = (GameInformation) m;
                        ItemEnum.generateCharMatrix(gameInformation.getBoard(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                                .printMatrix();
                    }*/
                    case "Waiting" -> System.out.println(((WaitingMessage) m).getS());
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }

    /*public Message receiveMessage() {
        try {
            String jsonString = in.nextLine();
            return c.convertFromJSON(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}