package it.polimi.ingsw;

import java.awt.event.ItemEvent;

public class Bookshelf {
    private ItemEnum[][] shelf = new ItemEnum[6][];
    private int[] heights = new int[5];

    //The "heights" array stores the available x-coordinates for each library column.

    Bookshelf(){
        int i;
        for(i=0; i<6; i++){
            shelf[i] = new ItemEnum[5];
        }
        for(i=0; i<5; i++)
            heights[i] = 0;
    }

    //"y" is the library column. The tile is placed on the first available row starting from the bottom.
    public void insert(int y, ItemEnum tile){
        if(heights[y]<=5) {
            shelf[5-heights[y]][y] = tile;
            heights[y]++;
        }else{
            System.out.println("The column is full!");
        }
    }

    public ItemEnum[][] getMatrix() {
        ItemEnum[][] copy = new ItemEnum[6][5];
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                copy[i][j] = shelf[i][j];
        return copy;
    }

    //TODO: potential error about the use of the "Card" class; check the class visibility.
    // At the moment there aren't any errors!
    //this method calculates the points achieved with the own PersonalCard
    public int pointPersonalCard(Card item){
        int i, points;
        Triplet control;

        points = 0;
        for(i=0; i<6; i++){
            control = item.getTriplet(i);
            if(control.getColor() == shelf[control.getX()][control.getY()]){
                points++;
            }
        }

        if(points == 3)
            points = 4;
        else if(points == 4)
            points = 6;
        else if(points == 5)
            points = 9;
        else if(points == 6)
            points = 12;

        return points;
    }

    public int adjacentTilesPoints(){
        int points, near, i, j;
        ItemEnum[][] copy = new ItemEnum[6][5];
        points = 0;
        near = 0;

        for(i=0; i<6; i++)
            for(j=0; j<5; j++)
                copy[i][j] = this.shelf[i][j];

        //TODO: to implement stack

        return points;
    }
}
