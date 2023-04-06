package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.MovePickCard;
import it.polimi.ingsw.view.CLI.CliView;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;


public class ControllerCli {
    private CliView view;
    private Model model;

    public ControllerCli(Model model, CliView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o != view || !(arg instanceof MovePickCard)){
            throw new IllegalArgumentException();
        }
        MovePickCard m = (MovePickCard) arg;
        if(model.isFeasiblePickMove(m.getRow(), m.getCol())){
            model.performMove(m.getRow(), m.getCol(), model.getPlayerMarker());
        } else {
            return;
        }
    }
}
