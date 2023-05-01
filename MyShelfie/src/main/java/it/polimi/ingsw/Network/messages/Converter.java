package it.polimi.ingsw.Network.messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;

public class Converter {

    public Message convertFromJSON(String message){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        String type = jsonObject.get("type").getAsString();
        switch(type) {
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
            case "GameInformation":
                return gson.fromJson(message, GameInformation.class);
            case "UsernameAnswer":
                return gson.fromJson(message, UsernameAnswer.class);
            case "Move":
                return gson.fromJson(message, MoveMessage.class);
            case "NotValidNumber":
                return gson.fromJson(message, NotValidNumberofPlayersMessage.class);
            case "NotValidUsername":
                return gson.fromJson(message, NotValidUsernameError.class);
            case "NotValidGameId":
                return gson.fromJson(message, NotValidGameIdError.class);
            case "GraphicalGameInformation":
                return gson.fromJson(message, GraphicalGameInfo.class);
            case "NotValidMove":
                return gson.fromJson(message, NotValidMove.class);
            case "ListOfLobbies":
                return gson.fromJson(message, ListOfLobbies.class);
            case "NotEnoughSpaceMove":
                return gson.fromJson(message, NotEnoughSpaceMoveError.class);
            case "InvalidColumn":
                return gson.fromJson(message, InvalidColumnError.class);
            case "EmptyPosition":
                return gson.fromJson(message, EmptyPositionError.class);
            case "NotAdjTiles":
                return gson.fromJson(message, NotAdjacTiles.class);
        }
        return null;
    }

    public String convertToJSON(Message m){
        Gson gson = new Gson();
        return gson.toJson(m);
    }
}
