package it.polimi.ingsw.model;

import java.util.Random;

public class Model {
    private Board board;
    private final int numPlayers;
    private Player[] players;
    private Player activePlayer;
    private Card[] personalCards;
    private int commonPoints1, commonPoints2;
    private int idActivePlayer;

    public Model(int numPlayers){
        this.numPlayers = numPlayers;
        board = new Board(this.numPlayers);
        personalCards = new Card[numPlayers];
        GeneratePersonalCards();
        for(int i=0; i<numPlayers; i++){
            players[i].setPersonalCard(personalCards[i]);
        }
        commonPoints1 = 8;
        commonPoints2 = 8;
        idActivePlayer = -1;
    }

    public boolean isFeasiblePickMove(int x, int y){
        return this.board.tileFreeSide(x, y);
    }

    public boolean checkFullShelf(){
        for(int i=0; i<5; i++){
            if(this.activePlayer.getHeights(i) != 6){
                return false;
            }
        }
        return true;
    }

    public void changeActivePlayer(){
        int i;
        boolean stop = false;
        for(i=0; i<this.numPlayers && !stop; i++) {
            if (activePlayer.equals(this.players[i])) {
                stop = true;
            }
        }
        i %= this.numPlayers;
        this.activePlayer = this.players[i];
    }

    public void setUsernamePlayer(String username){
        int i;
        this.idActivePlayer++;
        i = this.idActivePlayer;
        this.players[i] = new Player(username);
    }

    public int getNumPlayers(){
        return this.numPlayers;
    }

    public boolean duplicatedUsername(String x){
        int y = idActivePlayer;
        if(y==-1)
            return false;
        for(int i=0; i<y; i++){
            if(x.equals(players[i].getUsername()))
                return true;
        }
        return false;
    }

    /**
     * This method generates random Personal Cards which are contained in the class PersonalClass, (all different from one another).
     * @author Alessandro Fornara
     */
    private void GeneratePersonalCards(){
        int[] idPersonalCards = new int[numPlayers];
        Random rand = new Random();
        PersonalCard p = new PersonalCard();
        this.personalCards = new Card[numPlayers];

        if(this.numPlayers==2) {
            idPersonalCards[0] = rand.nextInt(12);
            idPersonalCards[1] = idPersonalCards[0];
            while (idPersonalCards[1] == idPersonalCards[0])
                idPersonalCards[1] = rand.nextInt(12);
            this.personalCards[0] = p.getCard(idPersonalCards[0]);
            this.personalCards[1] = p.getCard(idPersonalCards[1]);
        }
        if(this.numPlayers==3){
            idPersonalCards[2] = idPersonalCards[0];
            while (idPersonalCards[2]==idPersonalCards[0] || idPersonalCards[2]==idPersonalCards[1])
                idPersonalCards[2] = rand.nextInt(12);
            this.personalCards[2] = p.getCard(idPersonalCards[2]);
        }
        if(this.numPlayers==4){
            idPersonalCards[3] = idPersonalCards[0];
            while (idPersonalCards[3]==idPersonalCards[0] || idPersonalCards[3]==idPersonalCards[1] || idPersonalCards[3]==idPersonalCards[2])
                idPersonalCards[3] = rand.nextInt(12);
            this.personalCards[3] = p.getCard(idPersonalCards[3]);
        }
    }

    public void setFirstPlayer(){
        Random xyz = new Random();
        int i;
        i = xyz.nextInt(numPlayers);
        this.idActivePlayer = i;
    }

    public boolean enoughSpaceBookshelf(int x){
        if(x>activePlayer.maxTilesPick())
            return false;
        return true;
    }
}