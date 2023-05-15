package it.polimi.ingsw.Network.messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.ingsw.Network.messages.Answers.ACKMessage;
import it.polimi.ingsw.Network.messages.Answers.MoveAnswer;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.model.Model;

/**
 * This class is used to convert a {@link Message} using JSON
 * @author Alessandro Fornara
 */
public class Converter {

    /**
     * This method converts a message from a JSON string to a {@link Message}
     * @author Alessandro Fornara
     * @param message
     * @return a {@link Message} object
     */
    public Message convertFromJSON(String message){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        String type = jsonObject.get("type").getAsString();
        switch(type) {
            case "MoveMessage":
                return gson.fromJson(message, MoveMessage.class);
            case "ACK":
                return gson.fromJson(message, ACKMessage.class);
            case "FirstPlayer":
                return gson.fromJson(message, FirstPlayerMessage.class);
            case "Waiting":
                return gson.fromJson(message, WaitingMessage.class);
            case "NumberOfPlayers":
                return gson.fromJson(message, NumberOfPlayersAnswer.class);
            case "Lobby":
                return gson.fromJson(message, LobbyMessage.class);
            case "StartingGame":
                return gson.fromJson(message, StartingGameMessage.class);
            case "ChooseUsername":
                return gson.fromJson(message, ChooseUsernameMessage.class);
            case "GraphicalGameInfo":
                return gson.fromJson(message, GraphicalGameInfo.class);
            case "UsernameAnswer":
                return gson.fromJson(message, UsernameAnswer.class);
            case "Move":
                return gson.fromJson(message, MoveAnswer.class);
            case "NotValidNumber":
                return gson.fromJson(message, NotValidNumberofPlayersMessage.class);
            case "NotValidUsername":
                return gson.fromJson(message, NotValidUsernameError.class);
            case "NotValidGameId":
                return gson.fromJson(message, NotValidGameIdError.class);
            case "GraphicalGameInformation":
                return gson.fromJson(message, GraphicalGameInfo.class);
            case "NotValidMove":
                return gson.fromJson(message, NotValidMoveError.class);
            case "ListOfLobbies":
                return gson.fromJson(message, ListOfLobbies.class);
            case "NotEnoughSpaceColumn":
                return gson.fromJson(message, NotEnoughSpaceColumnError.class);
            case "InvalidColumn":
                return gson.fromJson(message, InvalidColumnError.class);
            case "EmptyPosition":
                return gson.fromJson(message, EmptyPositionError.class);
            case "NotAdjTiles":
                return gson.fromJson(message, NotAdjacTiles.class);
            case "NoFreeSide":
                return gson.fromJson(message, NoFreeSideError.class);
            case "NotEnoughSpaceBookshelf":
                return gson.fromJson(message, NotEnoughSpaceBookshelfError.class);
        }
        return null;
    }

    /**
     * This method converts a {@link Message} to a JSON string
     * @author Alessandro Fornara
     * @param m message
     * @return a string
     */
    public String convertToJSON(Message m){
        Gson gson = new Gson();
        return gson.toJson(m);
    }

    public String convertModelToJSON(Model model){
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public Model convertModelFromJSON(String model){
        Gson gson = new Gson();
        return gson.fromJson(model, Model.class);
    }
}
