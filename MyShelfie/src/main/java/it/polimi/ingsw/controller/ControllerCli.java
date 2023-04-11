package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.MovePickCard;
import it.polimi.ingsw.view.CLI.CliView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;


public class ControllerCli implements PropertyChangeListener {
    private CliView view;
    private Model model;

    public ControllerCli(Model model, CliView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent o) {

        MovePickCard m = (MovePickCard) o.getOldValue();
        if(model.isFeasiblePickMove(m.getRow(), m.getCol())){
            model.performMove(m.getRow(), m.getCol(), model.getPlayer(0));
        } else {
            return;
        }
    }
}
