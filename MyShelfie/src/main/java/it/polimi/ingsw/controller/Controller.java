package it.polimi.ingsw.controller;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.CliView;

public class Controller {
    private CliView view;
    private Model model;

    public Controller(int x){
        this.model = new Model(x);
    }
    public Controller(Model m) {this.model = m;}

    public int getNumPlayers(){
        return this.model.getNumPlayers();
    }

    public void setUsernamePlayer(String name){
        this.model.setUsernamePlayer(name);
    }

    public void setFirstPlayer(){
        model.setFirstPlayer();
    }

    public void setActivePlayer(int i){
        model.setActivePlayer(i);
    }

    public int getIdActivePlayer(){
        return model.getIdActivePlayer();
    }

    /**
     * @author Donato Fiore
     * @param input the move that the player wants to do
     * @return true if the player has inserted a wrong format of input
     * */
    public boolean dummyInput(String input){
        String[] coordinates;
        int len, i;

        len = input.length();
        if(len != 5 && len != 9 && len != 13) {
            return true;
        }
        for(i=1; i<len; i+=2){
            if(input.charAt(i) != ','){
                return true;
            }
        }
//  x,y,x,y,x,y,c
//  0123456789012

        coordinates = input.split(",");
        for(i=0; i<coordinates.length; i++){
            if(i==coordinates.length-1){
                //the character must be between '0' and '4', this is the column of bookshelf
                if(coordinates[i].charAt(0) < '0' || coordinates[i].charAt(0) > '4'){
                    //view.dummyInputPrint();
                    return true;
                }
            }else if(i%2 == 0){
                //the character must be between 'a' and 'z'
                if(coordinates[i].charAt(0) < 'a' || coordinates[i].charAt(0) > 'z'){
                    //view.dummyInputPrint();
                    return true;
                }
            }else if(i%2 != 0){
                //the character must be between '0' and '8'
                if(coordinates[i].charAt(0) < '0' || coordinates[i].charAt(0) > '8'){
                    //view.dummyInputPrint();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @author Donato Fiore
     * @param x the number of tiles that the player wants to take
     * @return true if 'x' is greater than the available space in the bookshelf
     * */
    public boolean enoughSpaceBookshelf(int x){
        boolean done;
        done = model.enoughSpaceBookshelf(x);
        return done;
    }

    /**
     * @author Donato Fiore
     * @param x the x's coordinate of the tile
     * @param y the y's coordinate of the tile
     * @return true if the selected tile is a blank tile
     * */
    private boolean blankTiles(int x, int y){
        boolean blank;
        ItemEnum[][] board;

        board = model.getBoardMatrix();
        blank = board[x][y].equals(ItemEnum.BLANK);
        /*if(blank)
            view.blankTilesSelected(x,y);*/

        return blank;
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @return true if at least one selected tile is a blank tile
     * */
    private boolean blankTiles(int x1, int y1, int x2, int y2){
        boolean done;
        done = blankTiles(x1,y1);
        if(done)
            return true;
        done = blankTiles(x2, y2);
        return done;
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @param x3 the x's coordinate of the third tile
     * @param y3 the y's coordinate of the third tile
     * @return true if at least one selected tile is a blank tile
     * */
    private boolean blankTiles(int x1, int y1, int x2, int y2, int x3, int y3){
        boolean done;
        done = blankTiles(x1,y1,x2,y2);
        if(done)
            return true;
        done = blankTiles(x3,y3);
        return done;
    }

    /**
     * @author Donato Fiore
     * @return true if the tile has at least one free side
     * */
    private boolean tileFreeSide(int x, int y){
        return model.tileFreeSide(x,y);
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @return true if the two tiles are adjacent; on the same 'x' coordinates or on the same 'y' coordinates, and they are next door
     * */
    private boolean adjacentTiles(int x1, int y1, int x2, int y2){
        return model.adjacentTiles(x1,y1,x2,y2);
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @param x3 the x's coordinate of the third tile
     * @param y3 the y's coordinate of the third tile
     * @return true if the two tiles are adjacent; on the same 'x' coordinates or on the same 'y' coordinates, and they are next door
     * */
    private boolean adjacentTiles(int x1, int y1, int x2, int y2, int x3, int y3){
        return model.adjacentTiles(x1,y1,x2,y2,x3,y3);
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @return false is the move isn't allowed: no tileFreeSide or the tiles aren't adjacent
     * */
    private boolean tileFreeSide(int x1, int y1, int x2, int y2){
        boolean done;

        done = model.tileFreeSide(x1,y1);
        if(!done)
            return false;
        done = model.tileFreeSide(x2,y2);
        if(!done)
            return false;

        return true;
    }

    /**
     * @author Donato Fiore
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @param x3 the x's coordinate of the third tile
     * @param y3 the y's coordinate of the third tile
     * @return false is the move isn't allowed: no tileFreeSide or the tiles aren't adjacent
     * */
    private boolean tileFreeSide(int x1, int y1, int x2, int y2, int x3, int y3){
        boolean done;
        done = model.tileFreeSide(x1,y1);
        if(!done)
            return false;
        done = model.tileFreeSide(x2,y2);
        if(!done)
            return false;
        done = model.tileFreeSide(x3,y3);
        if(!done)
            return false;

        return true;
    }


    /**
     * @author Donato Fiore
     * @return the code of the error or 0 if the move is done
     * ERROR CODES:
     * ERROR CODES:
     * 0: move done;
     * 1: blankTiles error;
     * 2: enoughSpaceBookshelf error;
     * 3: tileFreeSide error;
     * 4: adjacentTiles error;
     * 5: enoughSpaceColumn error;
     * */
    public int pickCard(int x, int y, int col){
        boolean done;

        done = blankTiles(x,y);
        if(done){
            return 1;
        }

        done = enoughSpaceBookshelf(1);
        if(!done)
            return 2;

        done = tileFreeSide(x,y);
        if(!done)
            return 3;

        if(!model.enoughSpaceColumn(col, 1)){
            return 5;
        }
        model.insert(x,y,col);
        return 0;
    }

    /**
     * @author Donato Fiore
     * @return the code of the error or 0 if the move is done
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @param col the column of the bookshelf where you want to insert the tile
     * ERROR CODES:
     * 0: move done;
     * 1: blankTiles error;
     * 2: enoughSpaceBookshelf error;
     * 3: tileFreeSide error;
     * 4: adjacentTiles error;
     * 5: enoughSpaceColumn error;
     * */
    public int pickCard(int x1, int y1, int x2, int y2, int col){
        boolean done;

        done = blankTiles(x1,y1,x2,y2);
        if(done){
            return 1;
        }

        done = enoughSpaceBookshelf(2);
        if(!done)
            return 2;

        done = tileFreeSide(x1,y1,x2,y2);
        if(!done)
            return 3;

        done = adjacentTiles(x1,y1,x2,y2);
        if(!done)
            return 4;

        done = model.enoughSpaceColumn(col, 2);
        if(!done)
            return 5;

        model.insert(x1,y1,x2,y2,col);
        return 0;
    }

    /**
     * @author Donato Fiore
     * @return the code of the error or 0 if the move is done
     * @param x1 the x's coordinate of the first tile
     * @param y1 the y's coordinate of the first tile
     * @param x2 the x's coordinate of the second tile
     * @param y2 the y's coordinate of the second tile
     * @param x3 the x's coordinate of the third tile
     * @param y3 the y's coordinate of the third tile
     * @param col the column of the bookshelf where you want to insert the tile
     * ERROR CODES:
     * 0: move done;
     * 1: blankTiles error;
     * 2: enoughSpaceBookshelf error;
     * 3: tileFreeSide error;
     * 4: adjacentTiles error;
     * 5: enoughSpaceColumn error;
     * */
    public int pickCard(int x1, int y1, int x2, int y2, int x3, int y3, int col){
        boolean done;

        done = blankTiles(x1,y1,x2,y2,x3,y3);
        if(done){
            return 1;
        }

        done = enoughSpaceBookshelf(3);
        if(!done)
            return 2;

        done = tileFreeSide(x1,y1,x2,y2,x3,y3);
        if(!done)
            return 3;

        done = adjacentTiles(x1,y1,x2,y2,x3,y3);
        if(!done)
            return 4;

        done = model.enoughSpaceColumn(col, 3);
        if(!done)
            return 5;

        model.insert(x1,y1,x2,y2,x3,y3,col);
        return 0;
    }

    /**
     * @author Donato Fiore
     * @param id the number of the common card, 0:CommonCard1; 1: CommonCard2
     * @return the points done with the CommonCard; if the goal isn't achieved, the points will be = 0;
     * */
    public int controlCommonCards(int id){
        boolean card;
        int points;

        points = model.getCommonCardsPoints(id);
        card = model.controlCommonCards(id);
        if(!card){
            points = 0;
        }
        return points;
    }

    /**
     * @author Donato Fiore
     * @return true if the match is finished
     * */
    public boolean finishTurn(){
        boolean turn;
        turn = model.finishTurn();
        return turn;
    }

    //TODO: finish this method
    /**
     * @author Donato Fiore
     * @return the points of the winner player
     * */
    public int declareWinner(){
        int x;
        x = model.theWinnerIs();
        return x;
    }

    public ItemEnum[][] getBoard(){
        return model.getBoardMatrix();
    }

    public String[] getCommonCardsDesigns(){
        return model.getCommonCardsDesigns();
    }

    /**
     * @author Alessandro Fornara
     * @return The active player's personal card
     */
    public Card getActivePlayerPersonalCard(){return model.getActivePlayerPersonalCard();}

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    /**
     * @author Alessandro Fornara
     * @return The active player's username
     */
    public String getActivePlayerUsername(){return model.getActivePlayerName();}

    /**
     * @author Alessandro Fornara
     * @return the active player's bookshelf
     */
    public ItemEnum[][] getActivePlayerShelf(){return model.getActivePlayerBookshelf();}

    public String getModelSave(){
        return model.saveModel();
    }

    public Card getPlayerPersonalCard(String username){
        return model.getPlayerPersonalCard(username);
    }

    public ItemEnum[][] getPlayerBookshelf(String username){
        return model.getPlayerBookshelf(username);
    }
}