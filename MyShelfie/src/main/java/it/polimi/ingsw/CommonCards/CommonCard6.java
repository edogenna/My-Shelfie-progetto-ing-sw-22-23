package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;

public class CommonCard6 implements CommonCardStrategy{
    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        return false;
    }
    @Override
    public void printCommonCard() {
        //TODO: implementation coming soon
        System.out.println("6");
    }
}
