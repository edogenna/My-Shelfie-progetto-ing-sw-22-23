package it.polimi.ingsw;

import java.awt.event.ItemEvent;
import java.util.Stack;

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
        int points, near, i, j, x, y;
        int[][] visited = new int[6][5];
        Stack<Couple> path = new Stack<Couple>();
        Couple search;
        boolean stop;

        points = 0;
        near = 0;

        for(i=0; i<6; i++){
            for(j=0; j<5; j++){
                visited[i][j] = 0;
            }
        }

        for(i=0; i<6; i++){
            for(j=0; j<5; j++){
                search = new Couple(i,j);
                path.push(search);
                visited[i][j] = 1;
                near = 1;
                stop = false;
                x = i;
                y = j;
                System.out.println("I = " + i + "; J = " + j);
                while(!path.isEmpty()){
                    while(!stop) {
                        System.out.println("x = " + x + "; y = "+ y);
                        if (x > 0 && this.shelf[x - 1][y] == this.shelf[x][y] && visited[x-1][y]==0) {
                            near++;
                            visited[x-1][y] = 1;
                            search.set(x - 1, y);
                            path.push(search);
                            x = x - 1;
                        } else if (y > 0 && this.shelf[x][y - 1] == this.shelf[x][y] && visited[x][y-1]==0) {
                            near++;
                            visited[x][y-1] = 1;
                            search.set(x, y - 1);
                            path.push(search);
                            y = y - 1;
                        } else if (y < 4 && this.shelf[x][y + 1] == this.shelf[x][y] && visited[x][y+1]==0) {
                            near++;
                            visited[x][y+1] = 1;
                            search.set(x, y + 1);
                            path.push(search);
                            y = y + 1;
                        } else if (x < 5 && this.shelf[x + 1][y] == this.shelf[x][y] && visited[x+1][y]==0) {
                            near++;
                            visited[x+1][y] = 1;
                            search.set(x + 1, y);
                            path.push(search);
                            x = x + 1;
                        } else {
                            stop = true;
                        }
                    }
                    //TODO: resolve the bug of the stack's "pop" method
                    search = path.pop();
                    x = search.getX();
                    y = search.getY();
                    System.out.println("POP: x = " + x + "; y = "+ y);
                    stop = false;
                }
                System.out.println("near = " + near);
                if(near == 3) {
                    points += 2;
                }
                else if(near == 4) {
                    points += 3;
                }
                else if(near == 5) {
                    points += 5;
                }
                else if(near >= 6) {
                    points += 8;
                }
                System.out.println("points = " + points);
            }
        }
        return points;
    }
}

class Couple{
    int x, y;

    Couple(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}