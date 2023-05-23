package it.polimi.ingsw.Network.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.polimi.ingsw.Network.messages.Answers.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.model.Model;

/**
 * This class is used to convert a {@link Message} using JSON
 * @author Alessandro Fornara
 */
public class Converter {

    //TODO: creare il costruttore come privato cosi da non piter instanzare questa classe
    private Converter() {}

    /**
     * This method converts a message from a JSON string to a {@link Message}
     * @param message a json string
     * @return a {@link Message} object
     */
    public static Message convertFromJSON(String message){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        String type = jsonObject.get("type").getAsString();
        return switch (type) {
            case "ACK" -> gson.fromJson(message, ACKMessage.class);
            case "ChatBegins" -> gson.fromJson(message, ChatBeginsMessage.class);
            case "ChatMessage" -> gson.fromJson(message, ChatMessage.class);
            case "ChooseUsername" -> gson.fromJson(message, ChooseUsernameMessage.class);
            case "CommonCard" -> gson.fromJson(message, CommonCardMessage.class);
            case "EmptyPosition" -> gson.fromJson(message, EmptyPositionError.class);
            case "FirstPlayer" -> gson.fromJson(message, FirstPlayerMessage.class);
            case "GraphicalGameInfo" -> gson.fromJson(message, GraphicalGameInfo.class);
            case "GraphicalGameInformation" -> gson.fromJson(message, GraphicalGameInfo.class);
            case "InvalidColumn" -> gson.fromJson(message, InvalidColumnError.class);
            case "ListOfLobbies" -> gson.fromJson(message, ListOfLobbies.class);
            case "Lobby" -> gson.fromJson(message, LobbyMessage.class);
            case "Move" -> gson.fromJson(message, MoveAnswer.class);
            case "MoveMessage" -> gson.fromJson(message, MoveMessage.class);
            case "NoFreeSide" -> gson.fromJson(message, NoFreeSideError.class);
            case "NotAdjTiles" -> gson.fromJson(message, NotAdjacTiles.class);
            case "NotEnoughSpaceBookshelf" -> gson.fromJson(message, NotEnoughSpaceBookshelfError.class);
            case "NotEnoughSpaceColumn" -> gson.fromJson(message, NotEnoughSpaceColumnError.class);
            case "NotValidGameId" -> gson.fromJson(message, NotValidGameIdError.class);
            case "NotValidMove" -> gson.fromJson(message, NotValidMoveError.class);
            case "NotValidNumber" -> gson.fromJson(message, NotValidNumberofPlayersMessage.class);
            case "NotValidUsername" -> gson.fromJson(message, NotValidUsernameError.class);
            case "NumberOfPlayers" -> gson.fromJson(message, NumberOfPlayersAnswer.class);
            case "OldGameId" -> gson.fromJson(message, OldGameId.class);
            case "OldGameIdAnswer" -> gson.fromJson(message, OldGameId.class);
            case "OldIdNotValid" -> gson.fromJson(message, OldIdNotValid.class);
            case "Reconnect" -> gson.fromJson(message, ReconnectionMessage.class);
            case "ReconnectionAnswer" -> gson.fromJson(message, ReconnectionAnswer.class);
            case "StartingGame" -> gson.fromJson(message, StartingGameMessage.class);
            case "Timeout" -> gson.fromJson(message, TimeoutMessage.class);
            case "TurnTimeOut" -> gson.fromJson(message, TurnTimeOut.class);
            case "UserIdMessage" -> gson.fromJson(message, UserIdMessage.class);
            case "UsernameAnswer" -> gson.fromJson(message, UsernameAnswer.class);
            case "Waiting" -> gson.fromJson(message, WaitingMessage.class);
            case "UnknownCode" -> gson.fromJson(message, UnknownCodeMessage.class);
            case "Win" -> gson.fromJson(message, WinMessage.class);
            default -> null;
        };
    }

    /**
     * This method converts a {@link Message} to a JSON string
     * @param m message
     * @return a string
     */
    static public String convertToJSON(Message m){
        Gson gson = new Gson();
        return gson.toJson(m);
    }

    /**
     * This method converts a {@link Model} to a JSON string
     * @param model model
     * @return a string
     */
    static public String convertModelToJSON(Model model){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(model);
    }

    /**
     * This method converts a JSON string into a {@link Model}
     * @param model a json string
     * @return a {@link Model} object
     */
    static public Model convertModelFromJSON(String model){
        Gson gson = new Gson();
        return gson.fromJson(model, Model.class);
    }
}
