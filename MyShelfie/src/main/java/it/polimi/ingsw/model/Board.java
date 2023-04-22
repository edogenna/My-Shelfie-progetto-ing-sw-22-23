package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCardGenerator;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;

import java.util.Random;

/**
 * Board represent the common board from which players take cards.
 *
 * @author Edoardo Gennaretti
 */
public class Board {
    public static final int BOARD_SIZE = 9;
    public static final int INITIAL_NUMBER_ITEMENUM = 20;
    private ItemEnum[][] matrix;
    private int numPlayers;
    private int[] numItemRemained = new int[ItemEnum.NUM_ITEMENUM];

    //TODO: modificare in un array in cui togliere i punti se si è in 3/4 giocatori
    //TODO: aggiungere funzione per togliere carta in una posizione, refill e prendere carta in una posizione

    private final static int[][] positionsAlwaysForbidden = new int[][]{{0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1},
            {3, 0}};
    private final static int numPosAlwaysForbidden = 9;
    private final static int[][] positionValid3Players = new int[][]{{0, 3}, {2, 2}};
    private final static int numPosValid3Players = 2;
    private final static int[][] positionValid4Players = new int[][]{{4, 0}, {3, 1}};
    private final static int numPosValid4Players = 2;
    private int[][] forbiddenPositions;
    private CommonCardStrategy[] CommonCards;

    public Board(int numPlayers) {
        matrix = new ItemEnum[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < ItemEnum.NUM_ITEMENUM; i++)
            numItemRemained[i] = INITIAL_NUMBER_ITEMENUM;
        refill();

        this.numPlayers = numPlayers;

        //Generating Common Goal Cards
        CommonCards =new CommonCardStrategy[2];
        CommonCardGenerator gen = new CommonCardGenerator();
        CommonCards = gen.GenerateCommonCards();
    }

    /**
     * Returns a copy of the matrix of the board.
     *
     * @author Edoardo Gennaretti
     * @return matrix of ItemEnum
     */
    public ItemEnum[][] getMatrix() {
        ItemEnum[][] copy = new ItemEnum[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                copy[i][j] = matrix[i][j];
        return copy;
    }

    private boolean isPositionValid(int r, int c) {
        for (int i = 0; i < numPosAlwaysForbidden; i++) {
            if (arePositionsEqual4Square(positionsAlwaysForbidden[i][0], positionsAlwaysForbidden[i][1], r, c))
                return false;
        }
        return true;
    }

    private boolean arePositionsEqual4Square(int r1, int c1, int r2, int c2) {
        return (r1 == r2 && c1 == c2) ||
                (r1 == BOARD_SIZE - 1 - c2 && c1 == r2) ||
                (r1 == BOARD_SIZE - 1 - r2 && c1 == BOARD_SIZE - 1 - c2) ||
                (r1 == c2 && c1 == BOARD_SIZE - 1 - r2);
    }

    /**
     * It returns the ItemEnum from a given position.
     *
     * @author Edoardo Gennaretti
     * @param r row of the matrix
     * @param c coloumn of the matrix
     * @return ItemEnum
     */
    public ItemEnum getItemEnum(int r, int c){
        if(matrix[r][c] == ItemEnum.BLANK)
            System.out.println("Restituita una carta Blank!");
        return matrix[r][c];
    }

    /**
     * It returns the ItemEnum from a given position and inserts a blank ItemEnum in that position.
     *
     * @author Edoardo Gennaretti
     * @param r row of the matrix
     * @param c coloumn of the matrix
     * @return ItemEnum
     */
    public ItemEnum deleteItemEnum(int r, int c){
        ItemEnum i = matrix[r][c];
        if(i == ItemEnum.BLANK)
            System.out.println("Eliminata una carta Blank!");
        else
            matrix[r][c] = ItemEnum.BLANK;
        return i;
    }

    /**
     * It refills the matrix following the rule of the game.
     *
     * @author Edoardo Gennaretti
     */
    public void refill(){
        Random rand = new Random();
        int n;
        ItemEnum item;

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isPositionValid(i, j)) {
                    n = rand.nextInt(ItemEnum.NUM_ITEMENUM); //blank is excluded
                    while (numItemRemained[n] == 0)
                        n = rand.nextInt(ItemEnum.NUM_ITEMENUM);

                    numItemRemained[n]--;
                    item = switch (n) {
                        case 0 -> ItemEnum.GREEN;
                        case 1 -> ItemEnum.WHITE;
                        case 2 -> ItemEnum.YELLOW;
                        case 3 -> ItemEnum.BLUE;
                        case 4 -> ItemEnum.AZURE;
                        case 5 -> ItemEnum.PURPLE;
                        default -> ItemEnum.BLANK; //covers also for case 6
                    };

                    matrix[i][j] = item;
                } else {
                    matrix[i][j] = ItemEnum.BLANK;
                }
            }
        }
    }


    /**
     * It returns if the matrix need to be refilled following the rule of the game
     *
     * @author Edoardo Gennaretti
     * @return true if the board need to be refilled, false otherwise
     */
    public boolean isRefillable(){
        for(int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(matrix[i][j] != ItemEnum.BLANK) {
                    for (int k = -1; k <= 1; k++) {
                        if (i + k >= 0 && i + k < BOARD_SIZE && matrix[i+k][j] != ItemEnum.BLANK)
                                return false;
                    }

                    for (int h = -1; h <= 1; h++) {
                        if (j + h >= 0 && j + h < BOARD_SIZE && matrix[i][j+h] != ItemEnum.BLANK)
                            return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * @author Alessandro Fornara
     * @return Array of CommonCardStrategy
     */
    //TODO: fix the array's pointer;
    public CommonCardStrategy[] getCommonCards(){
        return CommonCards;
    }

    public boolean tileFreeSide(int x, int y){
        boolean freeSide;

        freeSide = (x>0 && this.matrix[x-1][y] == ItemEnum.BLANK) || (x<8 && this.matrix[x+1][y] == ItemEnum.BLANK) || (y>0 && this.matrix[x][y-1] == ItemEnum.BLANK) || (y<8 && this.matrix[x][y+1] == ItemEnum.BLANK) || x==0 || y==0 || x==8 || y==8;

        return freeSide;
    }
}