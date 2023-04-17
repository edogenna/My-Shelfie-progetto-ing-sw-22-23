package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.CLI.CliView;

public class Controller {
    private CliView view;
    private Model model;

    public Controller(int x){
        model = new Model(x);
    }

    public Controller(Model model, CliView view){
        this.model = model;
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
}