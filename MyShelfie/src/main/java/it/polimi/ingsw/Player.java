package it.polimi.ingsw;

import java.util.Scanner;

public class Player {
    private int id, myPoints;
    public String username;
    Bookshelf myShelf;
    Card myGoals;

    Player(int id, String username){
        this.id = id;
        this.username = username;
        myPoints = 0;
        myShelf = new Bookshelf();
    }

    public void setPersonalCard(Card goals){
        myGoals = new Card();
        myGoals = goals;
    }

    //"calculatePoints" counts the points excluding the "common cards"
    public int calculatePoints(){
        myPoints = myShelf.pointPersonalCard(myGoals);
        myPoints += myShelf.adjacentTilesPoints();
        return myPoints;
    }

    public void fish(ItemEnum[][] tileBoard, int x1, int y1){
        boolean done, readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        done = false;
        readAgain = false;
        int y;

        while(!done){
            if(tileBoard[x1][y1] == ItemEnum.BLANK){
                readAgain = true;
                System.out.println("you have selected an empty position");
            }else if(tileBoard[x1-1][y1] == ItemEnum.BLANK || tileBoard[x1+1][y1] == ItemEnum.BLANK || tileBoard[x1][y1-1] == ItemEnum.BLANK || tileBoard[x1][y1+1] == ItemEnum.BLANK){
                done = true;
                readAgain = false;
            }else{
                System.out.println("The selected tile hasn't a free side");
                readAgain = true;
            }
            if(readAgain){
                System.out.println("Select another tile: x - y");
                x1 = readCoordinates.nextInt();
                y1 = readCoordinates.nextInt();
            }
        }
        //TODO: remove tile(x1, y1);
        System.out.println("Decide the column of your shelf");
        y = readCoordinates.nextInt();
        myShelf.insert(y,tileBoard[x1][y1]);
    }

    public void fish(ItemEnum[][] tileBoard, int x1, int y1, int x2, int y2){
        int j;
        boolean done, readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        done = false;
        readAgain = false;

        while(!done){
            if(tileBoard[x1][y1] == ItemEnum.BLANK || tileBoard[x2][y2] == ItemEnum.BLANK){
                System.out.println("you have selected an empty position");
                readAgain = true;
            }else{
                //the selected tiles aren't adjacent
                if(!((x1==x2 && (y1==y2+1 || y1==y2-1)) || (y1==y2 && (x1==x2+1 || x1==x2-1)))){
                    readAgain = true;
                    System.out.println("The tiles aren't adjacent");
                }else{
                    readAgain = false;
                }
            }
            //TODO: the selected tiles haven't got a free side
            if(!readAgain){
                if((tileBoard[x1+1][y1] == ItemEnum.BLANK || tileBoard[x1-1][y1] == ItemEnum.BLANK || tileBoard[x1][y1-1] == ItemEnum.BLANK || tileBoard[x1][y1+1] == ItemEnum.BLANK) && (tileBoard[x2+1][y2] == ItemEnum.BLANK || tileBoard[x2-1][y2] == ItemEnum.BLANK || tileBoard[x2][y2-1] == ItemEnum.BLANK || tileBoard[x2][y2+1] == ItemEnum.BLANK)){
                    done = true;
                }else
                    readAgain = true;
            }
            if(readAgain){
                System.out.println("Select another 2 tiles: x1 y1; x2 y2");
                x1 = readCoordinates.nextInt();
                y1 = readCoordinates.nextInt();
                x2 = readCoordinates.nextInt();
                y2 = readCoordinates.nextInt();
            }
        }
        //TODO: remove tile(x1, y1);
        System.out.println("Decide the column of your shelf");
        j = readCoordinates.nextInt();
        //TODO: create new method "insert" in bookshelf with another parameter: the number of tiles i want to insert
        myShelf.insert(j,tileBoard[x1][y1]);
    }

    //TODO: "fish" method with 3 tiles
}