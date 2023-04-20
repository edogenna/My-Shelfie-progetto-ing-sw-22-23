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
    private int idFirstPlayer, idActivePlayer;

    public Model(int numPlayers){
        this.numPlayers = numPlayers;
        this.board = new Board(this.numPlayers);
        this.personalCards = new Card[numPlayers];
        this.GeneratePersonalCards();
        this.players = new Player[numPlayers];
        commonPoints1 = 8;
        commonPoints2 = 8;
        idFirstPlayer = -1;
        idActivePlayer = 0;
    }

    public boolean isFeasiblePickMove(int x, int y){
        return this.board.tileFreeSide(x, y);
    }

    private void changeActivePlayer(){
        this.idActivePlayer++;
        this.idActivePlayer %= this.numPlayers;
        this.activePlayer = this.players[this.idActivePlayer];
    }

    public String getActivePlayerName(){
        return new String(this.activePlayer.getUsername());
    }

    public void setUsernamePlayer(String username){
        this.idFirstPlayer++;
        this.players[this.idFirstPlayer] = new Player(username);
        this.players[this.idFirstPlayer].setPersonalCard(personalCards[this.idFirstPlayer]);
    }

    public int getNumPlayers(){
        return this.numPlayers;
    }

    public boolean duplicatedUsername(String x){
        int y = idFirstPlayer;
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
        PersonalCards playerCard = new PersonalCards();
        this.personalCards = new Card[numPlayers];

        idPersonalCards[0] = rand.nextInt(12);
        idPersonalCards[1] = idPersonalCards[0];
        while (idPersonalCards[1] == idPersonalCards[0])
            idPersonalCards[1] = rand.nextInt(12);
        this.personalCards[0] = playerCard.getCard(idPersonalCards[0]);
        this.personalCards[1] = playerCard.getCard(idPersonalCards[1]);

        if(this.numPlayers >= 3){
            idPersonalCards[2] = idPersonalCards[0];
            while (idPersonalCards[2]==idPersonalCards[0] || idPersonalCards[2]==idPersonalCards[1])
                idPersonalCards[2] = rand.nextInt(12);
            this.personalCards[2] = playerCard.getCard(idPersonalCards[2]);
        }

        if(this.numPlayers == 4){
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
        this.idFirstPlayer = i;
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
        if(x == 1){
            done = this.activePlayer.getCommonDone1();
            y = this.commonPoints1;
        } else if (x == 2) {
            done = this.activePlayer.getCommonDone2();
            y = this.commonPoints2;
        }
        if(!done && board.getCommonCards()[x-1].checkBookshelf(this.activePlayer.getMatrixBookshelf())){
            this.activePlayer.updateCommonPoints(y,x);
            updateCommonPoints(x);
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

    private void updateCommonPoints(int numCard){
        if(numCard == 1){
            this.commonPoints1 -= 2;
        }else if(numCard == 2){
            this.commonPoints2 -= 2;
        }
    }

    public ItemEnum[][] getBoardMatrix(){
        return this.board.getMatrix();
    }

    public boolean finishTurn(){
        boolean finish;
        int x;

        finish = this.activePlayer.checkIfFull();
        if(finish){
            x = this.idActivePlayer+1;
            x %= this.numPlayers;
            if(x != this.idFirstPlayer)
                finish = false;
        }

        this.changeActivePlayer();
        if(board.isRefillable())
            board.refill();

        return finish;
    }

/*
    public ItemEnum[][] getCommonCards() {
        return
    }*/
}