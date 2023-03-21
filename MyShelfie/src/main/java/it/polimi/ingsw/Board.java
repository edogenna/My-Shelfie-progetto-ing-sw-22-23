package it.polimi.ingsw;

public class Board {
    private final int BOARD_SIZE = 9;
    private ItemEnum[][] matrix;
    private int numPlayers;

    //TODO: modificare in un array in cui togliere i punti se si è in 3/4 giocatori
    private static int[][] forbiddenPoints = new int[][]{{0,0},{0,1}};

    public Board(int numPlayers){
        matrix = new ItemEnum[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                matrix[i][j] = ItemEnum.BLANK;
        this.numPlayers = numPlayers;
    }
    public void printBoard(){
        for(int i = 0; i < BOARD_SIZE; i++){
            System.out.print(" "+i+" ");
        }
        System.out.print("\n");

        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                System.out.print(matrix[i][j].label + Costant.square + " " + ItemEnum.RESET);
            }
            System.out.print(" "+Costant.toChar(i)+"\n");
        }
    }



    //TODO: completare
    private boolean isPositionValid(int x, int y){ return true;}


}


class Point{
    public int x, y;
};