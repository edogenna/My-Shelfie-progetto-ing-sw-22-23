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

    public boolean duplicatedId(int x){
        return this.model.duplicatedId(x);
    }

    public void setIdUsernamePlayer(int id, String name){
        this.model.setIdUsernamePlayer(id, name);
    }
}
