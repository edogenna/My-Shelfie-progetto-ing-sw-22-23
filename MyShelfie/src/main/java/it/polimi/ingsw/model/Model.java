package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

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

    public String getActivePlayerName(){
        return new String(this.activePlayer.getUsername());
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
        PersonalCard playerCard = new PersonalCard();
        this.personalCards = new Card[numPlayers];

        if(this.numPlayers==2) {
            idPersonalCards[0] = rand.nextInt(12);
            idPersonalCards[1] = idPersonalCards[0];
            while (idPersonalCards[1] == idPersonalCards[0])
                idPersonalCards[1] = rand.nextInt(12);
            this.personalCards[0] = playerCard.getCard(idPersonalCards[0]);
            this.personalCards[1] = playerCard.getCard(idPersonalCards[1]);
        }
        if(this.numPlayers==3){
            idPersonalCards[2] = idPersonalCards[0];
            while (idPersonalCards[2]==idPersonalCards[0] || idPersonalCards[2]==idPersonalCards[1])
                idPersonalCards[2] = rand.nextInt(12);
            this.personalCards[2] = playerCard.getCard(idPersonalCards[2]);
        }
        if(this.numPlayers==4){
            idPersonalCards[3] = idPersonalCards[0];
            while (idPersonalCards[3]==idPersonalCards[0] || idPersonalCards[3]==idPersonalCards[1] || idPersonalCards[3]==idPersonalCards[2])
                idPersonalCards[3] = rand.nextInt(12);
            this.personalCards[3] = playerCard.getCard(idPersonalCards[3]);
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

    public boolean enoughSpaceColumn(int y, int num){
        return this.activePlayer.isColumnFull(y,num);
    }

    public boolean adjacentTiles(int x1, int y1, int x2, int y2){
        boolean isAdjacent;

        if(x1 == x2){
            isAdjacent = (y1 == y2 + 1) || (y1 == y2 - 1);
        }else if(y1 == y2){
            isAdjacent = (x1 == x2 + 1) || (x1 == x2 - 1);
        }else
            isAdjacent = false;

        return isAdjacent;
    }

    public boolean adjacentTiles(int x1, int y1, int x2, int y2, int x3, int y3){
        boolean isAdjacent;

        isAdjacent = (x1 == x2 && x2 == x3 && ((adjacentTiles(x1, y1, x2, y2) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x1, y1, x3, y3) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x2, y2, x1, y1) && adjacentTiles(x1, y1, x3, y3)))) || (y1 == y2 && y2 == y3 && ((adjacentTiles(x1, y1, x2, y2) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x1, y1, x3, y3) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x2, y2, x1, y1) && adjacentTiles(x1, y1, x3, y3))));


        return isAdjacent;
    }

    public void insert(int x, int y, int z){
        this.activePlayer.insert(z,this.board.deleteItemEnum(x,y));
    }

    public void insert(int x1, int y1, int x2, int y2, int z){
        this.activePlayer.insert(z, this.board.deleteItemEnum(x1,y1), this.board.deleteItemEnum(x2,y2));
    }

    public void insert(int x1, int y1, int x2, int y2, int x3, int y3, int col) {
        this.activePlayer.insert(col, this.board.deleteItemEnum(x1,y1), this.board.deleteItemEnum(x2,y2), this.board.deleteItemEnum(x3,y3));
    }

    public boolean controlCommonCards(int x){
        boolean done = false;
        int y=0;
        if(x==1){
            done = this.activePlayer.getCommonDone1();
            y = this.commonPoints1;
            commonPoints1-=2;
        } else if (x==2) {
            done = this.activePlayer.getCommonDone2();
            y = this.commonPoints2;
            commonPoints2-=2;
        }
        if(!done && board.getCommonCards()[x-1].checkBookshelf(this.activePlayer.getMatrixBookshelf())){
            this.activePlayer.updateCommonPoints(y,x);
            return true;
        }
        return false;
    }

    public int getCommonCardsPoints(int card){
        if(card == 1)
            return this.commonPoints1;
        else if(card == 2)
            return this.commonPoints2;
        return 0;
    }

    public ItemEnum[][] getBoardMatrix(){
        return this.board.getMatrix();
    }

/*
    public ItemEnum[][] getCommonCards() {
        return
    }*/
}