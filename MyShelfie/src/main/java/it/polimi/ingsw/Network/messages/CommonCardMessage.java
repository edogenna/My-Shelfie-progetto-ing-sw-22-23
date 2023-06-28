package it.polimi.ingsw.Network.messages;

/**
 * Message that is sent to the client when a common goal card is completed
 * It contains the username of the player that completed the card and the points he received
 * It is sent to all the players
 * @author Alessandro Fornara
 */
public final class CommonCardMessage extends Message{
    private final String s;

    public CommonCardMessage(String usrn, int idCommon, int points){
        super("CommonCard");
        if(idCommon == 1){
            this.s = usrn + " has completed the first common goal card and has been awarded " + points + " points";
        }
        else {
            this.s = usrn + " has completed the second common goal card and has been awarded " + points + " points";
        }
    }

    public String getS() {
        return s;
    }
}
