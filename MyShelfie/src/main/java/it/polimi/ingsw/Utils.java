package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods that could be useful in many occasions.
 * @author Alessandro Fornara
 */
public final class Utils {

    private Utils() {
    }

    /**
     * Sets all cells in a matrix to the indicated value
     * @param m     Matrix
     * @param value Number to be assigned
     */
    public static void setMatrix(int[][] m, int value) {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                m[i][j] = value;
    }

    /**
     * Sets all cells in an array to the indicated value
     * @param a     Array
     * @param value Number to be assigned
     */
    public static void setArray(int[] a, int value) {
        for (int i = 0; i < a.length; i++)
            a[i] = value;
    }

    /**
     * This method prints an Integer matrix
     * @param m matrix
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
     * @param a array
     */
    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }

    public static void mergeSort(List<String> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    private static void merge(List<String> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<String> leftList = new ArrayList<>(list.subList(left, left + n1));
        List<String> rightList = new ArrayList<>(list.subList(mid + 1, mid + 1 + n2));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}


