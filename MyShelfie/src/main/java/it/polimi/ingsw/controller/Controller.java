package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model m, View v){
        this.model=m;
        this.view=v;
    }


}
