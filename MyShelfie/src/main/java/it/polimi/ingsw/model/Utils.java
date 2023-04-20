package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

/**
 * This class contains methods that could be useful in many occasions.
 * @author Alessandro Fornara
 */
public class Utils {

    private Utils() {
    }

    /**
     * Sets all cells in a matrix to the indicated value
     *
     * @param m     Matrix
     * @param value Number to be assigned
     * @author Alessandro Fornara
     */
    public static void setMatrix(int[][] m, int value) {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                m[i][j] = value;
    }

    /**
     * Sets all cells in an array to the indicated value
     *
     * @param a     Array
     * @param value Number to be assigned
     * @author Alessandro Fornara
     */
    public static void setArray(int[] a, int value) {
        for (int i = 0; i < a.length; i++)
            a[i] = value;
    }

    /**
     * This method prints an Integer matrix
     *
     * @param m matrix
     * @author Alessandro Fornara
     */
    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] != -1)
                    System.out.print(" ");
                System.out.print(m[i][j]);
            }
        }
    }

    /**
     * This method prints an Integer array
     *
     * @param a array
     * @author Alessandro Fornara
     */
    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }
}
