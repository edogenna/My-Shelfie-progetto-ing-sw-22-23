package it.polimi.ingsw.model;

import java.util.Random;

public class Model {
    private Board board;
    private final int numPlayers;
    private Player[] players;
    private Player activePlayer;
    private Card[] personalCards;
    private int commonPoints1, commonPoints2;

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
    }

    public boolean isFeasiblePickMove(int x, int y){
        return this.board.tileFreeSide(x, y);
    }

    public void performMove(int x, int y, Player bob){
        int z = 1;
        bob.insert(z, board.deleteItemEnum(x,y));
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

    public void setIdUsernamePlayer(int i, int id, String username){
        this.players[i] = new Player(id, username);
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
}