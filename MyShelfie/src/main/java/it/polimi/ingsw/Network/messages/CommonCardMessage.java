package it.polimi.ingsw.Network.messages;

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
