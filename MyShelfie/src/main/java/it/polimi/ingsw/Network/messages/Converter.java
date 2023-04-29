package it.polimi.ingsw.Network.messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
                return gson.fromJson(message, GameStartMessage.class);
            case "ChooseUsername":
                return gson.fromJson(message, ChooseUsernameMessage.class);
            case "GameInformation":
                return gson.fromJson(message, GameInformation.class);
            case "UsernameAnswer":
                return gson.fromJson(message, UsernameAnswer.class);

        }
        return null;
    }

    public String convertToJSON(Message m){
        Gson gson = new Gson();
        return gson.toJson(m);
    }
}
