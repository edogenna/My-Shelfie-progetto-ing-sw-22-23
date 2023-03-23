package it.polimi.ingsw;

public class Bookshelf {
    private ItemEnum[][] shelf = new ItemEnum[6][];
    private int[] heights = new int[5];

    //The "heights" array stores the available x-coordinates for each library column.

    Bookshelf(){
        for(int i=0; i<5; i++){
            shelf[i] = new ItemEnum[5];
            heights[i] = 0;
        }
    }

    //"y" is the library column. The tile is placed on the first available row starting from the bottom.
    public void insert(int y, ItemEnum tile){
        if(heights[y]<=5) {
            shelf[heights[y]][y] = tile;
            heights[y]++;
        }else{
            System.out.println("The column is full!");
        }
    }

    //TODO: potential error about the use of the "Card" class; check the class visibility
    public int pointPersonalCard(Card item){
        int i, points;
        Triplet control;

        points = 0;
        for(i=0; i<6; i++){
            control = item.getTriplet(i);
            //TODO: finish the metod of comparing the tiles

        }

        return points;
    }
}
