package it.polimi.ingsw.model;

import com.google.gson.annotations.Expose;
import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.*;

import java.util.Random;

/**
 * Board represent the common board from which players take cards.
 * @author Edoardo Gennaretti
 */
public class Board {
    @Expose
    public static final int BOARD_SIZE = 9;
    @Expose
    public static final int INITIAL_NUMBER_ITEMENUM = 22;
    @Expose
    private ItemEnum[][] matrix;
    @Expose
    private int numPlayers;
    @Expose
    private int[] numItemRemained = new int[ItemEnum.NUM_ITEMENUM];

    //TODO: modificare in un array in cui togliere i punti se si è in 3/4 giocatori
    //TODO: aggiungere funzione per togliere carta in una posizione, refill e prendere carta in una posizione

    @Expose
    private final static int[][] positionsAlwaysForbidden = new int[][]{{0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1},
            {3, 0}};
    @Expose
    private final static int[][] positionValid3Players = new int[][]{{0, 3}, {2, 2}};
    @Expose
    private final static int[][] positionValid4Players = new int[][]{{4, 0}, {3, 1}};
    private CommonCardStrategy[] CommonCards;

    @Expose
    private int first = 0, second = 0;

    public void setMatrix(ItemEnum[][] matrix) {
        this.matrix = matrix;
    }


    public Board(int numPlayers) {
        matrix = new ItemEnum[BOARD_SIZE][BOARD_SIZE];

        if (numPlayers <= 1 || numPlayers > 4) {
            System.out.println("ERROR: The number of players must be between 2 and 4. It has been set to 2.");
            numPlayers = 2;
        }
        this.numPlayers = numPlayers;

        for (int i = 0; i < ItemEnum.NUM_ITEMENUM; i++)
            numItemRemained[i] = INITIAL_NUMBER_ITEMENUM;

        setBoardAllBlank();
        refill();

        //Generating Common Goal Cards
        CommonCards = new CommonCardStrategy[2];
        CommonCardGenerator gen = new CommonCardGenerator();
        CommonCards = gen.GenerateCommonCards();

        if(first == second)
            assignFirstAndSecond();

    }

    /**
     * Returns a copy of the matrix of the board.
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
        if (this.numPlayers == 2 || this.numPlayers == 3) {
            for (int[] ints : positionValid4Players) {
                if (arePositionsEqual4Square(ints[0], ints[1], r, c))
                    return false;
            }
        }

        //excluding positions valid for 3 players
        if (numPlayers == 2) {
            for (int[] ints : positionValid3Players) {
                if (arePositionsEqual4Square(ints[0], ints[1], r, c))
                    return false;
            }
        }
        return true;


    }

    /**
     * Compare the two positions, specularly in the 4 quadrants
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
     * @param r row of the matrix
     * @param c coloumn of the matrix
     * @return ItemEnum
     */
    public ItemEnum getItemEnum(int r, int c) {
        if (matrix[r][c] == ItemEnum.BLANK)
            System.out.println("Restituita una carta Blank!");
        return matrix[r][c];
    }

    /**
     *
     */
    private void setBoardAllBlank(){
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                matrix[i][j] = ItemEnum.BLANK;
    }

    public void setItemEnum(int r, int c, ItemEnum tile) {
        matrix[r][c] = tile;
    }


    /**
     * It returns the ItemEnum from a given position and inserts a blank ItemEnum in that position.
     * @param r row of the matrix
     * @param c coloumn of the matrix
     * @return ItemEnum
     */
    public ItemEnum deleteItemEnum(int r, int c) {
        ItemEnum i = matrix[r][c];
        if (i == ItemEnum.BLANK)
            System.out.println("Eliminata una carta Blank!");
        else
            matrix[r][c] = ItemEnum.BLANK;
        return i;
    }

    /**
     * It refills the matrix following the rule of the game.
     */
    public void refill() {
        Random rand = new Random();
        int n;
        ItemEnum item;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isPositionValid(i, j) && matrix[i][j] == ItemEnum.BLANK) {
                    n = rand.nextInt(ItemEnum.NUM_ITEMENUM); //blank is excluded

                    while (numItemRemained[n] <= 0)
                        n = (n + 1) % ItemEnum.NUM_ITEMENUM;

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
                }
            }
        }
    }


    /**
     * It returns if the matrix need to be refilled following the rule of the game
     * @return true if the board need to be refilled, false otherwise
     */
    public boolean isRefillable() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (matrix[i][j] != ItemEnum.BLANK) {
                    if(i - 1 >= 0 && matrix[i - 1][j] != ItemEnum.BLANK)
                        return false;
                    if(i + 1 < BOARD_SIZE && matrix[i + 1][j] != ItemEnum.BLANK)
                        return false;
                    if(j - 1 >= 0 && matrix[i][j - 1] != ItemEnum.BLANK)
                        return false;
                    if(j + 1 < BOARD_SIZE && matrix[i][j + 1] != ItemEnum.BLANK)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * @return Array of CommonCardStrategy
     */
    public CommonCardStrategy[] getCommonCards() {
        return CommonCards;
    }

    /**
     * This method is used to return designs of commonCards
     */
    public String[] getCommonCardDesigns() {
        String[] array = new String[2];
        for (int i = 0; i < 2; i++) {
            array[i] = CommonCards[i].getCommonCardDesign();
        }
        return array;
    }

    /**
     * Check if a tile has a free side
     * @param r row of the tile
     * @param c column of the tile
     * @return true if the tile has a free side, false otherwise
     */
    public boolean tileFreeSide(int r, int c) {
        return (r > 0 && this.matrix[r - 1][c] == ItemEnum.BLANK) || (r < 8 && this.matrix[r + 1][c] == ItemEnum.BLANK) ||
                (c > 0 && this.matrix[r][c - 1] == ItemEnum.BLANK) || (c < 8 && this.matrix[r][c + 1] == ItemEnum.BLANK) ||
                r == 0 || c == 0 || r == 8 || c == 8;
    }

    /**
     * This method assigns the number of two common cards, it is used when a game is loaded from the memory disk
     */
    private void assignFirstAndSecond() {
        this.first = CommonCards[0].getNumber();
        this.second = CommonCards[1].getNumber();
    }

    /**
     * This method sets common cards using two variables initialized when the game has been loaded from the memory disk
     */
    public void setCommonCards(){
        this.CommonCards = new CommonCardStrategy[2];
        switch (this.first) {
            case 1 -> CommonCards[0] = new CommonCard01();
            case 2 -> CommonCards[0] = new CommonCard02();
            case 3 -> CommonCards[0] = new CommonCard03();
            case 4 -> CommonCards[0] = new CommonCard04();
            case 5 -> CommonCards[0] = new CommonCard05();
            case 6 -> CommonCards[0] = new CommonCard06();
            case 7 -> CommonCards[0] = new CommonCard07();
            case 8 -> CommonCards[0] = new CommonCard08();
            case 9 -> CommonCards[0] = new CommonCard09();
            case 10 -> CommonCards[0] = new CommonCard10();
            case 11 -> CommonCards[0] = new CommonCard11();
            case 12 -> CommonCards[0] = new CommonCard12();
        }

        switch (this.second) {
            case 1 -> CommonCards[1] = new CommonCard01();
            case 2 -> CommonCards[1] = new CommonCard02();
            case 3 -> CommonCards[1] = new CommonCard03();
            case 4 -> CommonCards[1] = new CommonCard04();
            case 5 -> CommonCards[1] = new CommonCard05();
            case 6 -> CommonCards[1] = new CommonCard06();
            case 7 -> CommonCards[1] = new CommonCard07();
            case 8 -> CommonCards[1] = new CommonCard08();
            case 9 -> CommonCards[1] = new CommonCard09();
            case 10 -> CommonCards[1] = new CommonCard10();
            case 11 -> CommonCards[1] = new CommonCard11();
            case 12 -> CommonCards[1] = new CommonCard12();
        }
    }
}