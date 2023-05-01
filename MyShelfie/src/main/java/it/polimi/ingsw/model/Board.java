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
    private final static int[][] positionValid3Players = new int[][]{{0, 3}, {2, 2}};
    private final static int[][] positionValid4Players = new int[][]{{4, 0}, {3, 1}};
    private CommonCardStrategy[] CommonCards;

    public Board(int numPlayers) {
        matrix = new ItemEnum[BOARD_SIZE][BOARD_SIZE];

        if(numPlayers <= 1 || numPlayers > 4){
            System.out.println("ERROR: The number of players must be between 2 and 4. It has been set to 2.");
            numPlayers = 2;
        }
        this.numPlayers = numPlayers;

        for(int i = 0; i < ItemEnum.NUM_ITEMENUM; i++)
            numItemRemained[i] = INITIAL_NUMBER_ITEMENUM;

        refill();

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

    /**
     * Determines if a position is valid for filling
     *
     * @param r row
     * @param c column
     * @return true if the position is valid, false otherwise
     */
    private boolean isPositionValid(int r, int c) {
        //excluding positions always forbidden
        for (int[] ints : positionsAlwaysForbidden) {
            if (arePositionsEqual4Square(ints[0], ints[1], r, c))
                return false;
        }

        //excluding positions valid only for 4 players
        if(this.numPlayers == 2 || this.numPlayers == 3){
            for (int[] ints : positionValid4Players) {
                if (arePositionsEqual4Square(ints[0], ints[1], r, c))
                    return false;
            }
        }

        //excluding positions valid for 3 players
        if(numPlayers == 2){
            for (int[] ints : positionValid3Players) {
                if (arePositionsEqual4Square(ints[0], ints[1], r, c))
                    return false;
            }
        }
        return true;


    }

    /**
     * Compare the two positions, specularly in the 4 quadrants
     *
     * @param r1 first row
     * @param c1 first column
     * @param r2 second row
     * @param c2 second column
     * @return true if the positions are equal, false otherwise
     */
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

    public String[] getCommonCardDesigns(){
        String[] array = new String[2];
        for(int i=0; i<2; i++){
            array[i] = CommonCards[i].getCommonCardDesign();
        }
        return array;
    }

    /**
     * Check if a tile has a free side
     *
     * @param r row of the tile
     * @param c column of the tile
     * @return true if the tile has a free side, false otherwise
     */
    public boolean tileFreeSide(int r, int c){
        return (r>0 && this.matrix[r-1][c] == ItemEnum.BLANK) || (r<8 && this.matrix[r+1][c] == ItemEnum.BLANK) ||
                (c>0 && this.matrix[r][c-1] == ItemEnum.BLANK) || (c<8 && this.matrix[r][c+1] == ItemEnum.BLANK) ||
                r==0 || c==0 || r==8 || c==8;
        }
}