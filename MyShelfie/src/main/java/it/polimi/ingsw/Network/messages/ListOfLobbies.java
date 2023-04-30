package it.polimi.ingsw.Network.messages;

public class ListOfLobbies extends Message{
    private final String s = "Here's the list of available games, please select a game id: ";
     public ListOfLobbies(){
         super("ListOfLobbies");
     }
}
