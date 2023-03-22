package it.polimi.ingsw;

public class Board {
    private final int BOARD_SIZE = 9;
    private ItemEnum[][] matrix;
    private int numPlayers;

    //TODO: modificare in un array in cui togliere i punti se si è in 3/4 giocatori
    private final static int[][] positionsAlwaysForbidden = new int[][]{{0,0},{0,1},{0,2},
                                                                        {1,0},{1,1},{1,2},
                                                                        {2,0},{2,1},
                                                                        {3,0}};
    private final static int numPosAlwaysForbidden = 9;
    private final static int[][] positionValid3Players = new int[][]{{0,3},{2,2}};
    private final static int numPosValid3Players = 2;
    private final static int[][] positionValid4Players = new int[][]{{4,0},{3,1}};
    private final static int numPosValid4Players = 2;
    private int[][] forbiddenPositions;

    public Board(int numPlayers){
        matrix = new ItemEnum[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                if(isPositionValid(i,j))
                    matrix[i][j] = ItemEnum.generateRandomItemEnum();
                else
                    matrix[i][j] = ItemEnum.BLANK;
        this.numPlayers = numPlayers;
    }

    //TODO: modificare printBoard in modo che restituisca la sua matrice che va unita alle altre
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

    private boolean isPositionValid(int r, int c){
        for(int i = 0; i < numPosAlwaysForbidden; i++){
            if(arePositionsEqual4Square(positionsAlwaysForbidden[i][0],positionsAlwaysForbidden[i][1],r,c))
                return false;
        }
        return true;
    }

    private boolean arePositionsEqual4Square(int r1, int c1, int r2, int c2){
        return (r1 == r2 && c1 == c2) ||
                (r1 == BOARD_SIZE -1 -c2 && c1 == r2) ||
                (r1 == BOARD_SIZE -1 -r2 && c1 == BOARD_SIZE -1 -c2) ||
                (r1 == c2 && c1 == BOARD_SIZE -1 -r2);
    }


}
