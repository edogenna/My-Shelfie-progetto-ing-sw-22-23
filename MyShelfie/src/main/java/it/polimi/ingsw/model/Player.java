package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

import java.util.Scanner;

public class Player {
    private int id, myPoints, myCommonPoints;
    public String username;
    private Bookshelf myShelf;
    private Card myGoals;
    private boolean CommonDone1, CommonDone2;

    public Player(int id, String username){
        this.id = id;
        this.username = username;
        myPoints = 0;
        myCommonPoints = 0;
        myShelf = new Bookshelf();
        CommonDone1 = false;
        CommonDone2 = false;
    }

    public void setPersonalCard(Card goals){
        myGoals = new Card();
        myGoals = goals;
    }

    public ItemEnum[][] getMatrixBookshelf(){
        return myShelf.getMatrix();
    }
    //"calculatePoints" counts the points excluding the "common cards"
    public int calculatePoints(){
        myPoints = myShelf.pointPersonalCard(myGoals);
        myPoints += myShelf.adjacentTilesPoints();
        myPoints += myCommonPoints;
        return myPoints;
    }

    public boolean fullShelf(){
        return myShelf.checkIfFull();
    }

    public boolean pickCard(Board tileBoard, int x1, int y1){
        boolean readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        int y;
        ItemEnum removed;

        if(tileBoard.getMatrix()[x1][y1] == ItemEnum.BLANK){
            readAgain = true;
            System.out.println("you have selected an empty position.");
        }else if(tileFreeSide(tileBoard.getMatrix(),x1,y1)){
            //the tile has a free side
            readAgain = false;
        }else{
            System.out.println("one of the selected tiles hasn't a free side.");
            readAgain = true;
        }
/*
        if(readAgain){
            System.out.println("Select another tile: x - y");
            x1 = readCoordinates.nextInt();
            y1 = readCoordinates.nextInt();
        }
*/
        if(!readAgain){
            removed = tileBoard.deleteItemEnum(x1, y1);
            System.out.println("Decide the column of your shelf");
            //TODO: method of bookshelf class that show the possible column
            y = readCoordinates.nextInt();
            myShelf.insert(y, removed);
        }
        return !readAgain;
    }

    public boolean pickCard(Board tileBoard, int x1, int y1, int x2, int y2){
        int j;
        boolean readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        ItemEnum removed1, removed2;

        if(tileBoard.getMatrix()[x1][y1] == ItemEnum.BLANK || tileBoard.getMatrix()[x2][y2] == ItemEnum.BLANK){
            System.out.println("you have selected an empty position.");
            readAgain = true;
        }else{
            //the selected tiles aren't adjacent
            if(!adjacentTiles(x1, y1, x2, y2)){
                readAgain = true;
                System.out.println("The tiles aren't adjacent.");
            }else{
                readAgain = false;
            }
        }
        if(!readAgain){
            if(!(tileFreeSide(tileBoard.getMatrix(),x1,y1) && tileFreeSide(tileBoard.getMatrix(),x2,y2))){
                readAgain = true;
                System.out.println("one of the selected tiles hasn't a free side.");
            }
        }
/*            if(readAgain){
                System.out.println("Select another 2 tiles: x1 y1; x2 y2");
                x1 = readCoordinates.nextInt();
                y1 = readCoordinates.nextInt();
                x2 = readCoordinates.nextInt();
                y2 = readCoordinates.nextInt();
            }
 */
        if(!readAgain){
            removed1 = tileBoard.deleteItemEnum(x1,y1);
            removed2 = tileBoard.deleteItemEnum(x2,y2);
            System.out.println("Decide the column of your shelf");
            j = readCoordinates.nextInt();
            myShelf.insert(j, removed1, removed2);
        }
        return !readAgain;
    }

    public boolean pickCard(Board tileBoard, int x1, int y1, int x2, int y2, int x3, int y3){
        int j;
        boolean done, readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        ItemEnum removed1, removed2, removed3;

        if(tileBoard.getMatrix()[x1][y1] == ItemEnum.BLANK || tileBoard.getMatrix()[x2][y2] == ItemEnum.BLANK || tileBoard.getMatrix()[x3][y3] == ItemEnum.BLANK){
            System.out.println("you have selected an empty position.");
            readAgain = true;
        }else{
            //the selected tiles aren't adjacent
            if(!adjacentTiles(x1,y1,x2,y2,x3,y3)){
                readAgain = true;
                System.out.println("The tiles aren't adjacent.");
            }else{
                readAgain = false;
            }
        }
        if(!readAgain){
            //control no free side of the tiles
            if(!(tileFreeSide(tileBoard.getMatrix(),x1,y1) && tileFreeSide(tileBoard.getMatrix(),x2,y2) && tileFreeSide(tileBoard.getMatrix(),x3,y3))){
                readAgain = true;
                System.out.println("one of the selected tiles hasn't a free side.");
            }
        }
/*            if(readAgain){
            System.out.println("Select another 3 tiles: x1 y1; x2 y2; x3 y3");
            x1 = readCoordinates.nextInt();
            y1 = readCoordinates.nextInt();
            x2 = readCoordinates.nextInt();
            y2 = readCoordinates.nextInt();
            x3 = readCoordinates.nextInt();
            y3 = readCoordinates.nextInt();
        }*/
        if(!readAgain){
            removed1 = tileBoard.getMatrix()[x1][y1];
            removed2 = tileBoard.getMatrix()[x2][y2];
            removed3 = tileBoard.getMatrix()[x3][y3];
            System.out.println("Decide the column of your shelf");
            j = readCoordinates.nextInt();
            myShelf.insert(j, removed1, removed2, removed3);
        }
        return !readAgain;
    }

    //TODO: the private method "tileFreeSide", move this method in Board class
    private boolean tileFreeSide(ItemEnum[][] boardTest, int x, int y){
        boolean freeSide;

        freeSide = (x>0 && boardTest[x-1][y] == ItemEnum.BLANK) || (x<8 && boardTest[x+1][y] == ItemEnum.BLANK) || (y>0 && boardTest[x][y-1] == ItemEnum.BLANK) || (y<8 && boardTest[x][y+1] == ItemEnum.BLANK) || x==0 || y==0 || x==8 || y==8;

        return freeSide;
    }

    private boolean adjacentTiles(int x1, int y1, int x2, int y2){
        boolean isAdjacent;

        if(x1 == x2){
            isAdjacent = (y1 == y2 + 1) || (y1 == y2 - 1);
        }else if(y1 == y2){
            isAdjacent = (x1 == x2 + 1) || (x1 == x2 - 1);
        }else
            isAdjacent = false;

        return isAdjacent;
    }

    private boolean adjacentTiles(int x1, int y1, int x2, int y2, int x3, int y3){
        boolean isAdjacent;

        isAdjacent = (x1 == x2 && x2 == x3 && ((adjacentTiles(x1, y1, x2, y2) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x1, y1, x3, y3) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x2, y2, x1, y1) && adjacentTiles(x1, y1, x3, y3)))) || (y1 == y2 && y2 == y3 && ((adjacentTiles(x1, y1, x2, y2) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x1, y1, x3, y3) && adjacentTiles(x2, y2, x3, y3)) || (adjacentTiles(x2, y2, x1, y1) && adjacentTiles(x1, y1, x3, y3))));


        return isAdjacent;
    }
/*
x1 x2 x3
x1 x3 x2
x2 x1 x3
x2 x3 x1 not
x3 x1 x2 not
x3 x1 x2 not
*/


    /**
     * @author Alessandro Fornara
     * @return Returns the boolean value CommonDone1
     */
    public boolean getCommonDone1(){
        return this.CommonDone1;
    }
    /**
     * @author Alessandro Fornara
     * @return Returns the boolean value CommonDone2
     */
    public boolean getCommonDone2(){
        return this.CommonDone2;
    }
    /**
     * @author Alessandro Fornara
     * @return Returns the int value myCommonPoints
     */
    public int getMyCommonPoints(){
        return this.myCommonPoints;
    }

    /**
     * Assigns to MyCommonPoints a value
     * @author Alessandro Fornara
     * @param p int value
     */
    public void calculateCommonPoints1(int p){
        this.myCommonPoints += p;
        this.CommonDone1 = true;
    }

    public void calculateCommonPoints2(int p){
        this.myCommonPoints += p;
        this.CommonDone2 = true;
    }
}