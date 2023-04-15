package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.CLI.CliView;

public class ControllerCli{
    private CliView view;
    private Model model;

    public ControllerCli(Model model, CliView view){
        this.model = model;
        this.view = view;
    }
}
