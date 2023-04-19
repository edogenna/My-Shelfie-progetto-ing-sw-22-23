package it.polimi.ingsw.controller;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.CLI.CliView;

public class Controller {
    private CliView view;
    private Model model;

    public Controller(int x){
        model = new Model(x);
    }

    public Controller(int x, CliView view){
        this.model = new Model(x);
        this.view = view;
    }

    public int getNumPlayers(){
        return this.model.getNumPlayers();
    }

    public boolean duplicatedUsername(String x){
        return this.model.duplicatedUsername(x);
    }

    public void setUsernamePlayer(String name){
        this.model.setUsernamePlayer(name);
    }

    public void setFirstPlayer(){
        model.setFirstPlayer();
    }

    public boolean enoughSpaceBookshelf(int x){
        return model.enoughSpaceBookshelf(x);
    }

    public boolean isFeasibleMove(int x, int y){
        return model.isFeasiblePickMove(x,y);
    }

    public boolean pickCard(int x, int y, int z){
        if(!model.enoughSpaceBookshelf(z))
            return false;
        model.insert(x,y,z);
        return true;
    }

    //return true if someone has filled the bookshelf
    public boolean finishTurn(){
        int points;
        boolean card;
        card = model.controlCommonCards(0);
        if(card){
            points = model.getCommonCardsPoints(0);
            view.commonPoints(model.getActivePlayerName(), points, 0);
        }
        card = model.controlCommonCards(1);
        if(card){
            points = model.getCommonCardsPoints(1);
            view.commonPoints(model.getActivePlayerName(), points, 1);
        }
        card = model.checkFullShelf();
        model.changeActivePlayer();
        return card;
    }

    public ItemEnum[][] getBoard(){
        return model.getBoardMatrix();
    }

//    public ItemEnum[][] getCommonCards(int x){
//        return model.getCommonCards();
//    }
}