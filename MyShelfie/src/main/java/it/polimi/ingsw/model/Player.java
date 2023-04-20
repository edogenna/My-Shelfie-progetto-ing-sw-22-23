package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

import java.util.Scanner;
import java.util.Stack;

public class Player {
    private final int id;
    private int myPoints;
    private int myCommonPoints;
    private final String username;
    private Card myGoals;
    private boolean CommonDone1, CommonDone2;
    private ItemEnum[][] shelf = new ItemEnum[6][5];
    public Player(int id, String username){
        this.id = id;
        this.username = username;
        myPoints = 0;
        myCommonPoints = 0;
        CommonDone1 = false;
        CommonDone2 = false;
        myGoals = new Card();
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++)
                shelf[i][j] = ItemEnum.BLANK;
        }
    }

    public Player(String username){
        //todo: after cleaned the match class, remove the id
        this.id = -2;
        this.username = username;
        myPoints = 0;
        myCommonPoints = 0;
        CommonDone1 = false;
        CommonDone2 = false;
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++)
                shelf[i][j] = ItemEnum.BLANK;
        }
    }

    private int[] heights = new int[5];

    public void setPersonalCard(Card goals){
        this.myGoals = goals;
    }

    public String getUsername(){
        return new String(this.username);
    }

    public int getId(){
        return this.id;
    }

    public int getHeights(int i){
        return this.heights[i];
    }

    public ItemEnum[][] getMatrixBookshelf() {
        ItemEnum[][] copy = new ItemEnum[6][5];
        for(int i = 0; i < 6; i++)
            System.arraycopy(shelf[i], 0, copy[i], 0, 5);
        return copy;
    }

    //"calculatePoints" counts the points excluding the "common cards"
    public int calculatePoints(){
        myPoints = pointPersonalCard();
        myPoints += adjacentTilesPoints();
        myPoints += myCommonPoints;
        return myPoints;
    }

    //this method calculates the points achieved with the own PersonalCard
    private int pointPersonalCard(){
        int i, points;
        Triplet control;

        points = 0;
        for(i=0; i<6; i++){
            control = this.myGoals.getTriplet(i);
            if(control.getColor() == this.shelf[control.getX()][control.getY()]){
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

    private int adjacentTilesPoints(){
        int points, near, i, j, x, y;
        int[][] visited = new int[6][5];
        Stack<Integer> pathX = new Stack<Integer>();
        Stack<Integer> pathY = new Stack<Integer>();
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
                pathX.push(i);
                pathY.push(j);
                visited[i][j] = 1;
                near = 1;
                stop = false;
                x = i;
                y = j;
                while(!pathX.isEmpty()){
                    if(stop){
                        x = pathX.pop();
                        y = pathY.pop();
                    }
                    stop = false;

                    while(!stop) {
                        if (x > 0 && this.shelf[x - 1][y] == this.shelf[x][y] && visited[x-1][y]==0) {
                            near++;
                            visited[x-1][y] = 1;
                            pathX.push(x-1);
                            pathY.push(y);
                            x = x - 1;
                        } else if (y > 0 && this.shelf[x][y - 1] == this.shelf[x][y] && visited[x][y-1]==0) {
                            near++;
                            visited[x][y-1] = 1;
                            pathX.push(x);
                            pathY.push(y-1);
                            y = y - 1;
                        } else if (y < 4 && this.shelf[x][y + 1] == this.shelf[x][y] && visited[x][y+1]==0) {
                            near++;
                            visited[x][y+1] = 1;
                            pathX.push(x);
                            pathY.push(y+1);
                            y = y + 1;
                        } else if (x < 5 && this.shelf[x + 1][y] == this.shelf[x][y] && visited[x+1][y]==0) {
                            near++;
                            visited[x+1][y] = 1;
                            pathX.push(x+1);
                            pathY.push(y);
                            x = x + 1;
                        } else {
                            stop = true;
                        }
                    }

                }
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
            }
        }
        return points;
    }

    /**
     * This method checks if the bookshelf is full of tiles that are not BLANK.
     * @author Samuele Galli
     * @return true if is full, false otherwise.
     */
    public boolean checkIfFull(){
        for(int i=0; i<6; i++) {
            for(int j=0; j < 5; j++){
                if(this.shelf[i][j].equals(ItemEnum.BLANK)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIfFull2(){
        for(int i=0; i<5; i++) {
            if(this.heights[i] < 6)
                return false;
        }
        return true;
    }

    public boolean pickCard(Board tileBoard, int x1, int y1){
        boolean readAgain;
        Scanner readCoordinates = new Scanner(System.in);
        int j;
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
        if(!readAgain){
            removed = tileBoard.deleteItemEnum(x1, y1);
            System.out.println("Decide the column of your shelf");
            j = readCoordinates.nextInt();
            while(isColumnFull(j,1)){
                System.out.println("The column " +j+ "hasn't enough space, select another column");
                j = readCoordinates.nextInt();
            }
            insert(j, removed);
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
        if(!readAgain){
            removed1 = tileBoard.deleteItemEnum(x1,y1);
            removed2 = tileBoard.deleteItemEnum(x2,y2);
            System.out.println("Decide the column of your shelf");
            j = readCoordinates.nextInt();
            while(isColumnFull(j,2)){
                System.out.println("The column " +j+ " hasn't enough space, select another column");
                j = readCoordinates.nextInt();
            }
            insert(j, removed1, removed2);
        }
        return !readAgain;
    }

    public boolean pickCard(Board tileBoard, int x1, int y1, int x2, int y2, int x3, int y3){
        int j;
        boolean readAgain;
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
            removed1 = tileBoard.deleteItemEnum(x1, y1);
            removed2 = tileBoard.deleteItemEnum(x2, y2);
            removed3 = tileBoard.deleteItemEnum(x3, y3);
            System.out.println("Decide the column of your shelf");
            j = readCoordinates.nextInt();
            while(isColumnFull(j,3)){
                System.out.println("The column "+ j + " hasn't enough space, select another column");
                j = readCoordinates.nextInt();
            }
            insert(j, removed1, removed2, removed3);
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
    public int getMyPoints(){
        return this.myPoints;
    }

    /**
     * Adds to MyCommonPoints a value and sets CommonDone1 or CommonDone2 to true
     * @author Alessandro Fornara
     * @param p int value
     */
    public void updateCommonPoints(int p, int whichCard){
        if(whichCard==1) {
            this.myCommonPoints += p;
            this.CommonDone1 = true;
        }
        else if(whichCard==2) {
            this.myCommonPoints += p;
            this.CommonDone2 = true;
        }
    }

    /**
     * This method returns true if the column hasn't enough cells to cover the move
     * @author Donato Fiore
     * @param j column selected
     * @param n number of tiles to insert
     * @return true if the column has enough cells, false otherwise
     */
    public boolean isColumnFull(int j, int n){
        return this.heights[j] + n > 6;
    }

    public int maxTilesPick(){
        int x=0;
        for(int i=0; i<5; i++){
            if(6-this.getHeights(i)>x)
                x = 6 - this.getHeights(i);
        }
        return x;
    }

    //"y" is the library column. The tile is placed on the first available row starting from the bottom.
    public void insert(int y, ItemEnum tile){
            this.shelf[5-heights[y]][y] = tile;
            heights[y]++;
    }

    public void insert(int y, ItemEnum tile1, ItemEnum tile2){
            this.shelf[5-heights[y]][y] = tile1;
            heights[y]++;
            this.shelf[5-heights[y]][y] = tile2;
            heights[y]++;
    }

    public void insert(int y, ItemEnum tile1, ItemEnum tile2, ItemEnum tile3){
        this.shelf[5-heights[y]][y] = tile1;
        heights[y]++;
        this.shelf[5-heights[y]][y] = tile2;
        heights[y]++;
        this.shelf[5-heights[y]][y] = tile3;
        heights[y]++;
    }
}