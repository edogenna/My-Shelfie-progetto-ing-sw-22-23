package it.polimi.ingsw.GUI.controllers;

import it.polimi.ingsw.view.GuiView;

abstract public class ControllerGui {

    protected GuiView guiView;

    public void setGuiView(GuiView guiView) {
        this.guiView = guiView;
    }

    abstract public String getName();

    abstract public void update();
}
